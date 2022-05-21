package com.kotlin.projectapp

import android.app.AlertDialog
import android.app.ProgressDialog
import android.view.View
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CrudActivity : AppCompatActivity() {
    private lateinit var etID: EditText
    private lateinit var etNama: EditText
    private lateinit var etBuku: EditText
    private lateinit var etHarga: EditText
    private lateinit var etPayment: EditText
    private lateinit var btnsimpan: Button
    private lateinit var btnubah: Button
    private lateinit var btnhapus: Button
    private lateinit var btntampil: Button
    val database = FirebaseDatabase.getInstance("https://projectbookstoreapp-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("DATA PENJUALAN")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        etID = findViewById(R.id.etID)
        etNama = findViewById(R.id.etNama)
        etBuku = findViewById(R.id.etBuku)
        etHarga = findViewById(R.id.etHarga)
        etPayment = findViewById(R.id.etPayment)
        btnsimpan = findViewById(R.id.btnsimpan)
        btnubah = findViewById(R.id.btnubah)
        btnhapus = findViewById(R.id.btnhapus)
        btntampil = findViewById(R.id.btntampil)
        simpandata()
        ubahdata()
        hapusdata()
        tampildata()
    }

    fun tampilToast(text: String) {
        Toast.makeText(this@CrudActivity, text, Toast.LENGTH_LONG).show()
    }

    fun tampilDialog(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun bersihkanEditTexts() {
        etID.setText("")
        etNama.setText("")
        etBuku.setText("")
        etHarga.setText("")
        etPayment.setText("")
    }

    fun simpandata() {
        btnsimpan.setOnClickListener {
            val database = FirebaseDatabase.getInstance("https://projectbookstoreapp-default-rtdb.asia-southeast1.firebasedatabase.app/")
            val myRef = database.getReference("DATA PENJUALAN")
            val dialog: ProgressDialog = ProgressDialog(this);
            dialog.setMessage("Menyimpan")
            dialog.show()

            val data: Data = Data(
                etID.text.toString(),
                etNama.text.toString(),
                etBuku.text.toString(),
                etHarga.text.toString(),
                etPayment.text.toString(),
            )
            myRef.child(data.id).setValue(data).addOnCompleteListener {
                dialog.dismiss()
                tampilToast("Simpan Data Berhasil")
                bersihkanEditTexts()

            }
        }
    }

    fun ubahdata() {
        btnubah.setOnClickListener {
            val dialog: ProgressDialog = ProgressDialog(this);
            dialog.setMessage("Menyimpan")
            dialog.show()

            val data: Data = Data(
                etID.text.toString(),
                etNama.text.toString(),
                etBuku.text.toString(),
                etHarga.text.toString(),
                etPayment.text.toString(),
            )
            myRef.child(data.id).setValue(data).addOnCompleteListener {
                dialog.dismiss()
                tampilToast("Simpan Data Berhasil")
                bersihkanEditTexts()
            }
        }
    }

    fun hapusdata() {
        btnhapus.setOnClickListener {
            val dialog: ProgressDialog = ProgressDialog(this);
            dialog.setMessage("Mentimpan")
            dialog.show()

            val id: String = etID.text.toString()
            myRef.child(id).removeValue().addOnCompleteListener {
                dialog.dismiss()
                tampilToast("Hapus Data Berhasil")
            }
        }
    }

    fun tampildata() {
        btntampil.setOnClickListener(
            View.OnClickListener {

                val buffer = StringBuffer()
                myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(snapshoError: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val children = snapshot.children
                        children.forEach {
                            val data: Data = it.getValue(Data::class.java)!!
                            buffer.append(
                                "ID : " + data.id +
                                        "\n"
                            )
                            buffer.append(
                                "NAMA : " + data.nama +
                                        "\n"
                            )
                            buffer.append(
                                "NAMA BUKU : " + data.buku +
                                        "\n"
                            )
                            buffer.append(
                                "HARGA BUKU : " + data.harga +
                                        "\n"
                            )
                            buffer.append(
                                "PEMBAYARAN : " + data.payment +
                                        "\n\n"

                            )
                        }
                        tampilDialog("DATA PENJUALAN BUKU", buffer.toString())
                    }
                })
            }
        )
    }
}





