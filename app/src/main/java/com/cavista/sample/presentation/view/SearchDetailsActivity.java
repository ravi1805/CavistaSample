package com.cavista.sample.presentation.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cavista.sample.R;
import com.cavista.sample.domain.model.CommentDataModel;
import com.cavista.sample.domain.model.UISearchData;
import com.cavista.sample.domain.request.CommentReq;
import com.cavista.sample.presentation.BaseActivity;
import com.cavista.sample.presentation.adapter.CommentListAdapter;
import com.cavista.sample.presentation.utils.AppUtils;
import com.cavista.sample.presentation.utils.ViewModelFactory;
import com.cavista.sample.presentation.viewmodel.SearchDetailViewModel;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SearchDetailsActivity extends BaseActivity {

    private ImageButton imgSendButton;
    private EditText editTextSendComment;
    private ImageView imgSearchItem;
    private RecyclerView commentRecyclerview;
    private TextView noCommentView;
    private UISearchData uiSearchData = null;
    private CommentListAdapter commentListAdapter;
    @Inject
    ViewModelFactory viewModelFactory;

    private SearchDetailViewModel searchDetailViewModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_search_deatils);
        String jsonString = getIntent().getStringExtra(AppUtils.item_key);
        uiSearchData = new Gson().fromJson(jsonString, UISearchData.class);
        initView();
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        searchDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchDetailViewModel.class);
        searchDetailViewModel.getCommentListLiveData().observe(this, listResource -> {
            handleResponse(listResource);
        });

        searchDetailViewModel.getCommentList(uiSearchData.getId());
    }

    private void initRecyclerView() {
        commentRecyclerview = findViewById(R.id.commentRecyclerView);
        commentListAdapter = new CommentListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commentRecyclerview.setLayoutManager(layoutManager);
        commentRecyclerview.setAdapter(commentListAdapter);
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(uiSearchData.getTitle());
        imgSendButton = findViewById(R.id.btnSend);
        editTextSendComment = findViewById(R.id.editCommentField);
        imgSearchItem = findViewById(R.id.searchItemImgView);
        noCommentView = findViewById(R.id.noCommentView);
        AppUtils.INSTANCE.displayImage(this, uiSearchData.getImageUrl(), imgSearchItem);
        imgSendButton.setOnClickListener(v -> {
            searchDetailViewModel.postCommentList(new CommentReq(uiSearchData.getId(), editTextSendComment.getText().toString()));
            editTextSendComment.setText("");
        });
        editTextSendComment.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                //Implementation not needed
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //Implementation not needed
            }

            @Override
            public void afterTextChanged(Editable inputString) {
                if (inputString.toString().trim().length() > 0) {
                    imgSendButton.setEnabled(true);
                } else {
                    imgSendButton.setEnabled(false);
                }
            }
        });
    }

    private void handleResponse(List<CommentDataModel> commentList) {
        if (commentList.isEmpty()) {
            displayNoCommentView();
        } else {
            hideNoCommentView();
            commentListAdapter.setList(commentList);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void displayNoCommentView() {
        noCommentView.setVisibility(View.VISIBLE);
    }

    private void hideNoCommentView() {
        noCommentView.setVisibility(View.GONE);
    }
}
