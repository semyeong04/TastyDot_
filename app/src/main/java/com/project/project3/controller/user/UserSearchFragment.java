package com.project.project3.controller.user;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.project.project3.R;
import com.project.project3.adapterViewholder.CouponAdapter;
import com.project.project3.adapterViewholder.SearchAdapter;
import com.project.project3.model.SearchVO;
import com.project.project3.model.UserCouponVO;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserSearchFragment extends Fragment {

    private RecyclerView rv_search;

    private RequestQueue requestQueue;
    String storeName;
    String hashtag;
    String addr;
    String rate;
    ArrayList<SearchVO> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_search, container, false);
        rv_search = view.findViewById(R.id.rv_search);

        // 리스트 아이템 데이터 생성
        items = new ArrayList<>();

        // api 작성하기
        getStore();

        ArrayList<SearchVO> Searchs = items; //  경근씨 DB쿠폰 데이터 입력 ex20240122참고해서 만들면됩니다!

        SearchAdapter adapter = new SearchAdapter(Searchs);

        rv_search.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rv_search.setLayoutManager(manager);
        // 리사이클러뷰 새로고침 : adpater.notifyDatasetChanged();


        return view;
    }

    public void getStore() {
        String url = "http://192.168.219.101:8081/api/getStoreInfo"; // 가게 정보 조회 API 엔드포인트, 실제 엔드포인트로 수정 필요
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        // 응답으로 받은 JSON 배열을 순회하면서 가게 정보 처리
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject storeInfo = response.getJSONObject(i);
                             storeName = storeInfo.getString("storeName"); // 가게 이름
                             hashtag = storeInfo.getString("hashtag");
                             addr = storeInfo.getString("addr");
                            rate = storeInfo.getString("rate");
                            Log.d("name", storeName);
                            Log.d("hash", hashtag);
                            Log.d("addr", addr);
                            SearchVO vo1 = new SearchVO(storeName, addr, hashtag,rate, R.drawable.camera);
                            items.add(vo1);
                            Log.d("rate", rate);
                            rv_search.getAdapter().notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("실패", "가게정보 불러오는데 실패");
//                        Toast.makeText(getContext(), "가게 정보를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
//                    Toast.makeText(getContext(), "가게 정보를 불러오는 데 실패했습니다: " + error.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("에러", "가게정보 불러오는데 에러" + error.toString());
                });

        requestQueue.add(jsonArrayRequest); // requestQueue는 Volley의 RequestQueue 객체의 참조, 클래스 멤버 변수로 관리되어야 함
    }
}
