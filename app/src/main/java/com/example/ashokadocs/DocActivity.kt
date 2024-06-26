package com.example.ashokadocs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var dbref : DatabaseReference
private lateinit var userRecyclerview : RecyclerView
private lateinit var userArrayList : ArrayList<docs>
class DocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("DocActivity","ENTERED ONCREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)

        userRecyclerview=findViewById(R.id.doclist)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<docs>()
        getUserData()

    }
    private fun getUserData()
    {
        Log.e("DocActivity","ENTERED GET USER DATA")
        dbref=FirebaseDatabase.getInstance().getReference("Users")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(userSnapshot in snapshot.children)
                    {
                        val user=userSnapshot.getValue((docs ::class.java))
                        userArrayList.add(user!!)

                    }

                    var adap =Adapter(userArrayList)
                    userRecyclerview.adapter=adap
                    adap.setOnItemClickListener(object : Adapter.onItemClickListener{
                        override fun onCpyClick(position: Int, oper: Int) {
                            val currbox= userArrayList[position]

                            if(oper == 0)
                            {
                                Toast.makeText(this@DocActivity, "Copies :- ${currbox.Link}", Toast.LENGTH_SHORT).show()
                            }
                            else if(oper == 1)
                            {
                                Toast.makeText(this@DocActivity, "Downloads :- ${currbox.Drive}", Toast.LENGTH_SHORT).show()
                            }
                            else if(oper == 2)
                            {
                                Toast.makeText(this@DocActivity, "Status :- ${currbox.Status}", Toast.LENGTH_SHORT).show()
                            }
                        }


                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DocActivity", "Firebase operation cancelled: ${error.message}")
                Toast.makeText(this@DocActivity, "Failed to retrieve data. Please try again.", Toast.LENGTH_SHORT).show()
            }

        })

    }


}