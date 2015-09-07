package in.vs2.oneuptask;

import android.app.Application;

import com.auth0.lock.Lock;
import com.auth0.lock.LockProvider;

/**
 * Created by antarix on 07/09/15.
 */
public class OneUpApplication extends Application implements LockProvider {

    private Lock lock;

    public void onCreate() {
        super.onCreate();
        lock = new Lock.Builder()
                .loadFromApplication(this)
                        /** Other configuration goes here */
                .closable(true)
                .build();
    }

    @Override
    public Lock getLock() {
        return lock;
    }
}
