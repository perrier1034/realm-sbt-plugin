import android.support.test.runner.AndroidJUnit4
import com.github.aafa.model.User
import io.realm.RealmResults
import org.junit.Test
import org.junit.runner.RunWith

/**
  * Created by Alexey Afanasev on 25.02.16.
  */
@RunWith(classOf[AndroidJUnit4])
class RealmTestCase extends AbstractRealmTestCase{

  @Test
  def createUser() = {
    addUser(10, "demo")

    val firstUser: User = realm.where(classOf[User]).equalTo("id", new Integer(10)).findFirst()
    assert(firstUser.name == "demo")

    val tenthUser: User = realm.where(classOf[User]).equalTo("name", "demo").findFirst()
    assert(tenthUser.id == 10)
  }

  def addUser(id: Long = 1, name: String = "demo"): Unit = {
    val user: User = new User
    user.id = id
    user.name = name

    realmTransaction(_.copyToRealm(user))
  }

  @Test
  def deleteUser() = {
    addUser(2, "demo")
    val toDelete = realm.where(classOf[User]).equalTo("id", new Integer(2)).findAll()
    assert(toDelete.size() == 1)

    realmTransaction(_ => {toDelete.removeLast()})

    val emptyList: RealmResults[User] = realm.where(classOf[User]).findAll()
    assert(emptyList.isEmpty)
  }
}
