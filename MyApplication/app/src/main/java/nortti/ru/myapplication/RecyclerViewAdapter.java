package nortti.ru.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nortti.ru.myapplication.object.Dog;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private ArrayList<Dog> dogs;
    Dog dog;



    public RecyclerViewAdapter(ArrayList<Dog> dogs) {
        this.dogs = dogs;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name = null;
        private RoundImageView pic = null;



        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String link = dogs.get(getPosition()).getLink();
                    Intent i = new Intent(v.getContext(),DogInfoActivity.class);
                    i.putExtra("link",link);
                    v.getContext().startActivity(i);
                    Log.d(TAG,link);
                }
            });
            name = (TextView) v.findViewById(R.id.txName);
            pic = (RoundImageView) v.findViewById(R.id.Img);

        }




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        dog = dogs.get(i);
        viewHolder.name.setText(dog.getName());
        viewHolder.pic.setImageBitmap(dog.getPic());



    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }




}