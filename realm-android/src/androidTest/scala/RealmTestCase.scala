import android.support.test.runner.AndroidJUnit4
import com.github.aafa.model.User
import org.junit.Test
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

    realmTransaction(_.copyToRealm(user))

    val realmUser: User = realm.where(classOf[User]).equalTo("id", new Integer(10)).findFirst()
    assert(realmUser.name == "demo")
  }
}
