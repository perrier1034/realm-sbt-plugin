import android.content.Context
import android.support.test.InstrumentationRegistry
import android.test.RenamingDelegatingContext
import com.github.aafa.model.User
import io.realm.{Realm, RealmConfiguration}
import io.realm.RealmConfiguration.Builder
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
    realm.beginTransaction()
    realm.clear(classOf[User])
    realm.commitTransaction()
  }
}
