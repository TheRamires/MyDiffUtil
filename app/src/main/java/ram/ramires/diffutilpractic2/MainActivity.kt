package ram.ramires.diffutilpractic2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction=supportFragmentManager.beginTransaction()
        val isAdded=supportFragmentManager.findFragmentByTag("blank")?.isAdded
        if (isAdded==null || !isAdded){
            fragmentTransaction.replace(R.id.container, BlankFragment(),"blank")
            fragmentTransaction.commit()
        }

    }
}