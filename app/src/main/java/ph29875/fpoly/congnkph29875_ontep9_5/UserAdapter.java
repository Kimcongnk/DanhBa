package ph29875.fpoly.congnkph29875_ontep9_5;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<DanhBa> userList;
    private Dao dao;

    public UserAdapter(List<DanhBa> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_db, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhBa user = userList.get(position);
        holder.nameTextView.setText(user.getHoTen());
        holder.phoneTextView.setText(user.getSoDt());
        holder.emailTextView.setText(user.geteMail());
        holder.noteTextView.setText(user.getGhiChu());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Thông tin chi tiết");
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.thongtin_chitiet, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dao = new Dao(v.getContext());

        final TextView edtTen = dialogView.findViewById(R.id.nameTextView);
        final TextView edtSdt = dialogView.findViewById(R.id.phoneTextView);
        final TextView edtEmail = dialogView.findViewById(R.id.emailTextView);
        final TextView edtGhichu = dialogView.findViewById(R.id.noteTextView);

        edtTen.setText(user.getHoTen());
        edtSdt.setText(user.getSoDt());
        edtEmail.setText(user.geteMail());
        edtGhichu.setText(user.getGhiChu());
        Toast.makeText(v.getContext(), "dsdsd", Toast.LENGTH_SHORT).show();
    }
});
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Sửa");
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.them_danhba, null);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                dao = new Dao(v.getContext());

                final EditText edtTen = dialogView.findViewById(R.id.editTextText);
                final EditText edtSdt = dialogView.findViewById(R.id.editTextText2);
                final EditText edtEmail = dialogView.findViewById(R.id.editTextText3);
                final EditText edtGhichu = dialogView.findViewById(R.id.editTextText4);

                edtTen.setText(user.getHoTen());
                edtSdt.setText(user.getSoDt());
                edtEmail.setText(user.geteMail());
                edtGhichu.setText(user.getGhiChu());

                final Button ok = dialogView.findViewById(R.id.button3);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setHoTen(edtTen.getText().toString().trim());
                        user.setSoDt(edtSdt.getText().toString().trim());
                        user.seteMail(edtEmail.getText().toString().trim());
                        user.setGhiChu(edtGhichu.getText().toString().trim());
                        dao.updateFood(user);
                        userList =  dao.getAllData();
                        notifyDataSetChanged();
                        Toast.makeText(v.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new Dao(v.getContext());
             dao.deleteFoodById(user.getIb());
            userList =  dao.getAllData();
             notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView phoneTextView;
        public TextView emailTextView;
        public TextView noteTextView;
        public ImageView sua, xoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            noteTextView = itemView.findViewById(R.id.noteTextView);
            sua = itemView.findViewById(R.id.imageView);
            xoa = itemView.findViewById(R.id.imageView2);
        }
    }
}
