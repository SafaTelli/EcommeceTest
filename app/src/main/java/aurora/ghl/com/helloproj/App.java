package aurora.ghl.com.helloproj;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by safa telli on 18/10/2019.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        //initialize Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
