package com.example.retrofitstewdzasan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitstewdzasan.databinding.ActivityMainBinding
import com.example.retrofitstewdzasan.model.Post
import com.example.retrofitstewdzasan.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPostById(1)

        observeViewModel()


        binding.btnSubmit.setOnClickListener {
            viewModel.pushPost2(2, 2, "@Field@FormUrlEncoded", "x-www-form-urlencoded")
        }

//        val myPost = Post(2, 2, "@Body", "Application/JSON")
//        binding.btnSubmit.setOnClickListener {
//            viewModel.pushPost(myPost)
//        }

//        val options = HashMap<String, String>()
//        options["_sort"] = "id"
//        options["_order"] = "desc"

//        binding.btnSubmit.setOnClickListener {
//            if (binding.etIdNumber.text.isNotEmpty()) {
//                val id = binding.etIdNumber.text.toString()
//                if (id.toInt() in 1..10) {
//                    viewModel.getSortedPostListByUserId2(id.toInt(), options)
//                } else {
//                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Toast.makeText(this, "Please Enter ID Number", Toast.LENGTH_SHORT).show()
//            }
//        }


//        binding.btnSubmit.setOnClickListener {
//            if (binding.etIdNumber.text.isNotEmpty()) {
//                val id = binding.etIdNumber.text.toString()
//                if (id.toInt() in 1..10) {
//                    viewModel.getSortedPostListByUserId(id.toInt(),"id","desc")
////                    viewModel.getSortedPostListByUserId(id.toInt(),"id","asc")
//                } else {
//                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Toast.makeText(this, "Please Enter ID Number", Toast.LENGTH_SHORT).show()
//            }
//        }


//        binding.btnSubmit.setOnClickListener {
//            if (binding.etIdNumber.text.isNotEmpty()) {
//                val id = binding.etIdNumber.text.toString()
//                if (id.toInt() in 1..10) {
//                    viewModel.getPostListByUserId(id.toInt())
//                } else {
//                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Toast.makeText(this, "Please Enter ID Number", Toast.LENGTH_SHORT).show()
//            }
//        }


//        binding.btnSubmit.setOnClickListener {
//            if (binding.etIdNumber.text.isNotEmpty()) {
//                val id = binding.etIdNumber.text.toString()
//                if (id.toInt() in 1..100) {
//                    viewModel.getPostById(id.toInt())
//                } else {
//                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                Toast.makeText(this, "Please Enter ID Number", Toast.LENGTH_SHORT).show()
//            }
//        }


        binding.previous.setOnClickListener {
            viewModel.getPreviousPost()
        }

        binding.next.setOnClickListener {
            viewModel.getNextPost()
        }
    }

    private fun observeViewModel() {

        viewModel.post.observe(this) { response ->
            if (response.isSuccessful) {
                binding.tvName.text = response.code().toString()
            } else {
                binding.tvName.text = response.code().toString()
            }
        }

//        viewModel.post.observe(this) { response ->
//            if (response.isSuccessful) {
//                binding.tvName.text =
//                    response.body()?.userId.toString() + "\n" + response.body()?.id.toString() + "\n" + response.body()?.title + "\n" + response.body()?.body
//            } else {
//                binding.tvName.text = response.code().toString()
//            }
//        }

//        viewModel.postList.observe(this) { response ->0
//            if (response.isSuccessful) {
//                response.body()?.forEach {
//                    Log.d("TAGTAG", it.id.toString())
//                    Log.d("TAGTAG", it.title)
//                    Log.d("TAGTAG", it.body)
//                }
//            } else {
//                binding.tvName.text = response.code().toString()
//            }
//        }

    }

}