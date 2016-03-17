import android.support.test.runner.AndroidJUnit4
import com.github.aafa.Realm._
import io.realm.RealmResults
import org.junit.Test
import org.junit.runner.RunWith

/**
  * Created by Alexey Afanasev on 25.02.16.
  */
@RunWith(classOf[AndroidJUnit4])
class RealmScalaTestCase extends AbstractRealmTestCase{

  @Test
  def testScala() = {
    RealmVersion == "0.1.0-SNAPSHOT"
  }
}
