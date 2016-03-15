import android.content.Context
import android.support.test.InstrumentationRegistry
import android.test.RenamingDelegatingContext
import com.github.aafa.model.User
import io.realm.RealmConfiguration.Builder
import io.realm.{Realm, RealmConfiguration}
import org.junit.Before

/**
  * Created by Alexey Afanasev on 15.03.16.
  */
abstract class AbstractRealmTestCase {
  var mMockContext: Context = null

  lazy val realmConfiguration: RealmConfiguration = new Builder(mMockContext).deleteRealmIfMigrationNeeded().build()
  lazy val realm: Realm = Realm.getInstance(realmConfiguration)

  @Before
  def setup() = {
    mMockContext = new RenamingDelegatingContext(InstrumentationRegistry.getInstrumentation.getTargetContext, "test_")
    clearData
  }

  def clearData: Unit = {
    realmTransaction(_.clear(classOf[User]))
  }

  def realmTransaction(action: Realm => Unit) = {
    realm.beginTransaction()
    action(realm)
    realm.commitTransaction()
  }
}
