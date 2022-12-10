package com.example.cvbuilder.ui

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.cvbuilder.R
import com.google.android.material.tabs.TabLayout
import com.example.cvbuilder.common.CommonConstrains.MSharedPreference
import com.example.cvbuilder.common.CommonConstrains.USER_REPO
import com.example.cvbuilder.common.Constrains
import com.example.cvbuilder.models.Certification
import com.example.cvbuilder.models.Education
import com.example.cvbuilder.models.Experience
import com.example.cvbuilder.models.User
import com.example.cvbuilder.ui.adapters.ViewPagerAdapter
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeActivity : AppCompatActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

        setSupportActionBar(tab_toolbar)
        getUserData()
        tab_tablayout.setupWithViewPager(tab_viewpager)
        CoroutineScope(Dispatchers.IO)
            .launch {
                getUserName()
            }
        setupViewPager(tab_viewpager)


    }

    private fun getUserData() {
        if (MSharedPreference?.contains("userData") == true)
            this.user = getUserDataFromSP()
        else
            this.user = getUserDataFromRunTime()
    }

    private fun getUserDataFromRunTime(): User {
        val user = User()
        user.fName = "Wesam"
        user.lName = "Alqudah"
        user.jobTitle = "Software Consultant "
        user.image_url = R.drawable.personal_image
        user.careerNote = "Completed on-compus studies and currently taking distance education " +
                "courses to complete a Master's Degree in Computer Science (Available for full-time, W2 employment)."
        val workMap = HashMap<String, List<String>>()
        workMap[Constrains.languages] = arrayListOf("Java", "Java Script", "C++","Kotlin")
        workMap[Constrains.frameworks_Web] =
            arrayListOf("Spring(Boot, MVC, AOP, Dependency Injection, eureka, Batch)", "Hibernates", "JDBC","React","Microservice","Node"," Redux","Junit", "Mockito")
        workMap[Constrains.Microservices_cloud] =
            arrayListOf("Docker", "Kafka")
        workMap[Constrains.databases] =
            arrayListOf( "MySQL","Postgres","Cassandra", "MangoDB", "CosmosDB")
        workMap[Constrains.tools] =
            arrayListOf("InteliJ IDEA", "Postman,", "Git", "WebStorm","Visual Studio Code,", "UML")
        user.workExperience = workMap

        //About Me
        user.aboutMe =
            "Full Stack Engineer with 4+ years of experience in Designing, Developing, and implementing highly scalable and maintainable applications and solutions in Java, Spring Boot, ReactJS, JavaScript, NodeJS, Microservices, Data Structures, Algorithms, Web Development, Object Oriented Analysis and Design, Design Patterns."
        val educationList=ArrayList<Education>()
        val certificationList=ArrayList<Certification>()
        educationList.add(
            Education(1,R.drawable.miu,
            "Maharishi International University",
        "Master of Science in Computer Science")
        )
        educationList.add(
            Education(2,R.drawable.albayt,
                "Al Albayt University",
                "Bachelor of Science in Computer Information System")
        )
        user.educationList=educationList
        certificationList.add(
            Certification(1,R.drawable.java,
                "OCA Java SE 8 Programmer I",2020
            )
        )
        certificationList.add(
            Certification(2,R.drawable.micro,
            "Microsoft Certified Fundamentals",2021)
        )
        user.certificationList=certificationList

        val workExperienceList=ArrayList<Experience>()
        workExperienceList.add(
            Experience(
                1,R.drawable.wlmart,
                "Software Consultant",
                "at Walmart through KiteString" ,
                "Oct 2022 - Present",
                "Tx, USA",
                "-."
            )
        )
        workExperienceList.add(
            Experience(
                1,R.drawable.aau,
                "Software Developer",
                "A.A.U" ,
                "Feb 2020 - Mar 2021",
                "Amman, Jordan",
                "Developing Registration system."
            )
        )
        workExperienceList.add(
            Experience(
                1,R.drawable.jod,
                "Full-Stack Developer (JAVA/JAVASCRIPT)",
                "JoD" ,
                "Feb 2017 - Jan 2020",
                "Amman, Jordan",
                "Developing Projects depends on business logic"
            )
        )

        user.workExperienceList=workExperienceList

        user.phone="+1(929)634-0615"
        user.email="DEV@gmail.com"
        user.linkedIn="https://www.linkedin.com/in/wesam-alqudah1/"
        user.github="https://github.com/WesamAlqudah"
        user.pdf="PDF"
        MSharedPreference?.let {
            it.edit()?.putString("userData",Json.encodeToString(user))
        }
        return user
    }

    private fun getUserDataFromSP(): User {
        return Gson()
            .fromJson(
                MSharedPreference?.getString("userData", ""), User::class.java
            )
    }

    private fun setupViewPager(tabViewpager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment.newInstance(user), "Home")
        adapter.addFragment(AboutMeFragment.newInstance(user), "About Me")
        adapter.addFragment(WorkFragment.newInstance(user), "Work")
        adapter.addFragment(ContactFragment.newInstance(user), "Contact")

        tabViewpager.adapter = adapter
    }

    private suspend fun getUserName(): String {
        val userId = intent.extras?.getLong("userId")
        val user = USER_REPO?.getUserUsingID(userId.toString())
        return user?.fName + " " + user?.lName
//        if (user!=null)
//            findViewById<TextView>(R.id.tv_welcome).text= "Welcome ${user.fName} ${user.lName}"
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_tellegram -> showSendMessageDialog("Telegram")
        R.id.action_linkedIn -> showSendMessageDialog("LinkedIn")
        R.id.action_whatsapp -> showSendMessageDialog("Whatsapp")
        R.id.action_gmail -> showSendMessageDialog("Gmail")
        else -> super.onOptionsItemSelected(item)
    }

    private fun showSendMessageDialog(s: String): Boolean {
        AlertDialog.Builder(this)
            .setTitle(s)
            .setMessage("Would you like to send a Message using $s Application")
            .setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            .create()
            .show()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}