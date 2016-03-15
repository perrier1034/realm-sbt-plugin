import android.support.test.runner.AndroidJUnit4
import com.github.aafa.model.{Role, Email, User}
import io.realm.{RealmQuery, RealmResults}
import org.junit.Test
import org.junit.runner.RunWith

/**
  * Created by Alexey Afanasev on 25.02.16.
  */
@RunWith(classOf[AndroidJUnit4])
class RelationshipsTestCase extends AbstractRealmTestCase{

  def addUser(id: Long = 1, name: String = "su"): Unit = {
    realmTransaction(realm => {
      val user: User = realm.createObject(classOf[User])
      user.id = id
      user.name = name

      val email: Email = realm.createObject(classOf[Email])
      email.value = "admin@l33t.com"
      user.email = email

      val role: Role = realm.createObject(classOf[Role])
      role.name = "admin"
      user.role = role
    })
  }

  @Test
  def createUserWithRelations() = {
    addUser()

    val realmQuery: RealmQuery[User] = realm.where(classOf[User])

    assert(realmQuery.equalTo("id", new Integer(1)).findFirst().name == "su")
    assert(realmQuery.equalTo("name", "su").findFirst().id == 1)
    assert(realmQuery.equalTo("email.value", "admin@l33t.com").findFirst().id == 1)
    assert(realmQuery.equalTo("role.name", "admin").findFirst().id == 1)

  }

}
