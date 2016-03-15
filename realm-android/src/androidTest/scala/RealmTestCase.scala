import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.RenamingDelegatingContext
import com.github.aafa.model.User
import io.realm.{Realm, RealmConfiguration}
import io.realm.RealmConfiguration.Builder
import org.junit.{Test, Before}
import org.junit.runner.RunWith

/**
  * Created by Alexey Afanasev on 25.02.16.
  */
@RunWith(classOf[AndroidJUnit4])
class RealmTestCase {
  var mMockContext: Context = null

  @Before
  def setup() = {
    mMockContext = new RenamingDelegatingContext(InstrumentationRegistry.getInstrumentation.getTargetContext, "test_")
    clearData
  }

  lazy val realmConfiguration: RealmConfiguration = new Builder(mMockContext).deleteRealmIfMigrationNeeded().build()
  lazy val realm: Realm = Realm.getInstance(realmConfiguration)

  def clearData: Unit = {
    realm.beginTransaction()
    realm.clear(classOf[User])
    realm.commitTransaction()
  }

  @Test
  def test() = {
    val user: User = new User
    user.id = 10
    user.name = "demo"
    assert(user.name == "demo")

    realm.beginTransaction()
    realm.copyToRealm(user)
    realm.commitTransaction()

    val realmUser: User = realm.where(classOf[User]).equalTo("id", new Integer(10)).findFirst()
    assert(realmUser.name == "demo")
  }
}
