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
class RealmTestCase extends AbstractRealmTestCase{

  @Test
  def createUser() = {
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
