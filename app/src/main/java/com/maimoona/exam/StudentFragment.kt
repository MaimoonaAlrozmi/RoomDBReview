package com.maimoona.exam

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_fragment.*

private lateinit var btn: Button;

class StudentFragment : Fragment(), DialogAddNewStudent.Callbacks {

    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var noDataLinearayout:LinearLayout

    private var adapter: StudentsAdapter? = null

    private val studentListViewModel: StudentsListViewModel by lazy {
        ViewModelProviders.of(this).get(StudentsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.student_fragment, container, false)
        /* btn=view.findViewById(R.id.btnOne);

         btn.setOnClickListener {
             Toast.makeText(context,"welcome to Codeing Academy",Toast.LENGTH_LONG).show();
         }*/

        studentRecyclerView = view.findViewById(R.id.rv) as RecyclerView
        noDataLinearayout=view.findViewById(R.id.ly_noData) as LinearLayout
        studentRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
        return view;
    }

    private fun updateUI() {
        val std = studentListViewModel.students
        adapter = StudentsAdapter(std)
        studentRecyclerView.adapter = adapter

    }


    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        val nameTextView: TextView = itemView.findViewById(R.id.stu_name) as TextView
        val noTextView: TextView = itemView.findViewById(R.id.stu_no) as TextView
        val passTextView: TextView = itemView.findViewById(R.id.stu_pass) as TextView
        val deleteTextView: TextView = itemView.findViewById(R.id.delete_std) as TextView

        private lateinit var student: Students;

        init {
            deleteTextView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(context, "Maimoona", Toast.LENGTH_LONG).show();
            onStudentDeleted(adapterPosition)
        }


        fun bind(student: Students) {
            this.student = student
            nameTextView.text = this.student.stdNmae
            noTextView.text = "Number :" + this.student.stdNo.toString()
            passTextView.text = "pass : " + this.student.stdPass.toString()
        }

    }

    private inner class StudentsAdapter(var students: List<Students>) :
        RecyclerView.Adapter<StudentHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : StudentHolder {
            val view = layoutInflater.inflate(R.layout.item_list_fragment, parent, false)
            return StudentHolder(view)
        }

        override fun getItemCount(): Int {
            Log.d("mmmmmmm", "size= ${students.size}")

            if(students.isEmpty()){
                noDataLinearayout.visibility=View.VISIBLE
            }else{
                noDataLinearayout.visibility=View.GONE
            }

            return students.size
        }


        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val std = students[position]
            holder.apply {
                nameTextView.text = std.stdNmae
                noTextView.text = std.stdNo.toString()
                passTextView.text = std.stdPass.toString()

            }
        }
    }


    companion object {
        fun newInstance(): StudentFragment {
            return StudentFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.new_student -> {

                // studentListViewModel.addStudent(Students(UUID.randomUUID(),"Maimoona",2,true))
                //updateUI()
                DialogAddNewStudent().apply {
                    setTargetFragment(this@StudentFragment, 0)
                    show(this@StudentFragment.requireFragmentManager(), "Input")
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStudentAdded(student: Students) {
        studentListViewModel.addStudent(student)
        updateUI()
    }

    override fun onStudentDeleted(position: Int) {
        studentListViewModel.deleteStudent(position)
        updateUI()
    }

}