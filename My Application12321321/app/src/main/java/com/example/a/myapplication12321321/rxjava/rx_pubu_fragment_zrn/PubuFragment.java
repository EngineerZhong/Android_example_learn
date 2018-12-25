package com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a.myapplication12321321.R;
import com.example.a.myapplication12321321.base.mvp.BaseFragment;
import com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.data.PubuZrnAdapter;
import com.example.a.myapplication12321321.rxjava.rx_pubu_fragment_zrn.data.Zrn;

import java.util.ArrayList;
import java.util.List;
/**
 * Time:2018/12/19 - 11:10
 * Author:Zhongwb
 * Description:
 */
public class PubuFragment extends BaseFragment implements PubuContract.PubuView {

    RecyclerView rvPubuFr;
    private PubuContract.PubuPresenter mPresenter;
    private PubuZrnAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setListener() {

    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(getMyContext()).inflate(R.layout.fragment_pubu_zrn, null);
        rvPubuFr = view.findViewById(R.id.rv_pubu_fr);
        return view;
    }

    @Override
    public void weightOnClick(View view) {

    }

    @Override
    public void doBussiness() {
        List<Zrn> data = new ArrayList<>();
        String[] urls = new String[]{
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660314&di=c0811afa961b846567cbde6a31ef18f6&imgtype=0&src=http%3A%2F%2Fmp1.iqiyipic.com%2Fimage%2F20181119%2Fc9%2F91%2Fppu_574934560102_pp_601_300_300.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660589&di=29b70889b6604eac445b776aefbde531&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F69%2Fw500h369%2F20181210%2FxX4P-hpinryc9715343.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660585&di=5131dd8bc66358017aadecc2b4c680a5&imgtype=0&src=http%3A%2F%2Fp1.hoopchina.com.cn%2Fgdc%2Fweibo%2Fpic%2F24dccf0fc7835ddfbf73b8e410ad09be.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660584&di=d849e1a87c33791be833bbf0cfd6d39c&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F6543127340%2F1000",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545200660584&di=2255235dd8fa0258159692ef28133981&imgtype=0&src=http%3A%2F%2Fpic.rmb.bdstatic.com%2F4ea8ff7dfb7dfbbbafa19f2ad295b74b.jpeg%40wm_2%2Ct_55m%2B5a625Y%2B3L%2BW9seinhua1qeWTpQ%3D%3D%2Cfc_ffffff%2Cff_U2ltSGVp%2Csz_23%2Cx_15%2Cy_15"
        };
        for(String url : urls){
            data.add(new Zrn(url,"章若楠，1996年11月出生于浙江省温州市，中国内地女演员，" +
                    "毕业于杭州电子科技大学。2017年12月，在电影《悲伤逆流成河》中饰演顾森湘。"));
        }
        adapter = new PubuZrnAdapter(data,R.layout.pubu_zrn_item,getMyContext());
        adapter.setGridLayoutManager(rvPubuFr);
        rvPubuFr.setAdapter(adapter);
    }

    @Override
    public void setPresenter(PubuContract.PubuPresenter presenter) {
        if (presenter == null) {
            throw new NullPointerException("PubuPresenter NullPointerException");
        }
        this.mPresenter = presenter;
    }

    @Override
    public void showError(String error) {
        showToastShort(error);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

}
