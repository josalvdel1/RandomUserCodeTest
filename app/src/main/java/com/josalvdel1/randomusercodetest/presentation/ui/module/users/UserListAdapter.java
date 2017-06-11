package com.josalvdel1.randomusercodetest.presentation.ui.module.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josalvdel1.randomusercodetest.R;
import com.josalvdel1.randomusercodetest.di.scope.ActivityScope;
import com.josalvdel1.randomusercodetest.domain.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

@ActivityScope
public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private UserListPresenter userListPresenter;

    private List<User> users;

    @Inject
    public UserListAdapter(UserListPresenter userListPresenter) {
        this.userListPresenter = userListPresenter;
        this.users = new ArrayList<>();
    }

    public void addAll(Collection<User> collection) {
        users.clear();
        users.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.item_user_layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder UserViewHolder = (UserViewHolder) holder;
        User User = users.get(position);
        UserViewHolder.render(User);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addAll(List<User> users) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new UserDiffCallback(users));
        diffResult.dispatchUpdatesTo(this);
        this.users = users;
    }

    public void add(User user) {
        users.add(user);
        notifyItemInserted(users.indexOf(user));
    }

    public void remove(int position) {
        users.remove(position);
        notifyItemRemoved(position);
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void render(User User) {
        }

        private Context getContext() {
            return itemView.getContext();
        }
    }

    private class UserDiffCallback extends DiffUtil.Callback {

        private List<User> newUsers;

        public UserDiffCallback(List<User> newUsers) {
            this.newUsers = newUsers;
        }

        @Override
        public int getOldListSize() {
            return users.size();
        }

        @Override
        public int getNewListSize() {
            return newUsers.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            User oldUser = users.get(oldItemPosition);
            User newUser = newUsers.get(newItemPosition);
            return oldUser.equals(newUser);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            User oldUser = users.get(oldItemPosition);
            User newUser = newUsers.get(newItemPosition);

            String oldUserFirstName = oldUser.getFirstName();
            String newUserFirstName = newUser.getFirstName();
            boolean firstNameEquality = oldUserFirstName != null
                    && oldUserFirstName.equals(newUserFirstName);

            String oldUserLastName = oldUser.getLastName();
            String newUserLastName = newUser.getLastName();
            boolean lastNameEquality = oldUserLastName != null
                    && oldUserLastName.equals(newUserLastName);

            String oldUserEmail = oldUser.getEmail();
            String newUserEmail = newUser.getEmail();
            boolean emailEquality = oldUserEmail != null
                    && oldUserEmail.equals(newUserEmail);

            String oldUserPhone = oldUser.getPhone();
            String newUserPhone = newUser.getPhone();
            boolean phoneEquality = oldUserPhone != null
                    && oldUserPhone.equals(newUserPhone);

            return firstNameEquality && lastNameEquality && emailEquality && phoneEquality;
        }
    }
}