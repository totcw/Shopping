package com.betterda.shopping.find;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.betterda.mylibrary.Utils.StatusBarCompat;
import com.betterda.shopping.R;
import com.betterda.shopping.base.BaseFragment;
import com.betterda.shopping.find.contract.FindContract;
import com.betterda.shopping.find.presenter.FindPresenterImpl;
import com.betterda.shopping.home.MainActivity;
import com.betterda.shopping.utils.UtilMethod;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/8.
 */

public class FindFragment extends BaseFragment<FindContract.Presenter> implements FindContract.View, BaiduMap.OnMyLocationClickListener, BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener {
    @BindView(R.id.bmapView)
    FrameLayout mBmapView;
    /**
     * 地图功能
     */
    private TextureMapView mMapView;
    private BaiduMap mBaiduMap;

    /**
     * 定位功能
     */
    public LocationClient mLocationClient = null; //定位的类
    public BDLocationListener myListener = new MyLocationListener();

    private LatLng ll;
    private RoutePlanSearch mSearch;
    private WalkingRouteOverlay overlay;

    @Override
    public View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_find, null);
    }

    @Override
    protected FindContract.Presenter onLoadPresenter() {
        return new FindPresenterImpl();
    }

    @Override
    public void initData() {
        super.initData();

        addMap();
        setMap();

        //创建线路规划
        mSearch = RoutePlanSearch.newInstance();
        startLocation();


    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {//隐藏
        } else {
            StatusBarCompat.setStatusBar5(getmActivity(), R.color.white);
            ((MainActivity)getmActivity()).getmBvMain().setVisibility(View.GONE);
        }
    }


    /**
     * 初始化地图相关
     */
    private void addMap() {
        BaiduMapOptions baiduMapOptions = new BaiduMapOptions();
        //隐藏缩放的控件
        baiduMapOptions.zoomControlsEnabled(false);
        //隐藏比例的控件
        baiduMapOptions.scaleControlEnabled(false);
        //创建地图控件
        mMapView = new TextureMapView(getmActivity(), baiduMapOptions);
        mBaiduMap = mMapView.getMap();
        //将地图添加到布局中
        mBmapView.addView(mMapView);
    }


    /**
     * 设置地图相关参数
     */
    private void setMap() {
        //设置缩放级别
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.
                newMapStatus(new MapStatus.Builder().zoom(19).build()));
        //设置当前位置的点击事件
        mBaiduMap.setOnMyLocationClickListener(this);
        //设置marker的点击事件
        mBaiduMap.setOnMarkerClickListener(this);
        //设置地图的点击事件
        mBaiduMap.setOnMapClickListener(this);
    }

    /**
     * 开始定位相关
     */
    private void startLocation() {
        /**
         * 定位相关
         */
        mLocationClient = new LocationClient(getmActivity());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        UtilMethod.initLocation(mLocationClient);
        //开启定位
        mLocationClient.start();
    }


    /**
     * 默认位置的点击事件
     *
     * @return
     */
    @Override
    public boolean onMyLocationClick() {
        //showPaoPao();
        showInfoWindow(ll);
        return true;
    }

    /**
     * marker的点击事件
     *
     * @param marker
     * @return
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        //吊起百度地图
/*        // 构建 导航参数
// 天安门坐标
        double mLat1 = 39.915291;
        double mLon1 = 116.403857;
// 百度大厦坐标
        double mLat2 = 40.056858;
        double mLon2 = 116.308194;
        LatLng pt1 = new LatLng(mLat1, mLon1);
        LatLng pt2 = new LatLng(mLat2, mLon2);

        NaviParaOption para = new NaviParaOption()
                .startPoint(pt1).endPoint(pt2)
                .startName("天安门").endName("百度大厦");

        try {
// 调起百度地图步行导航
            BaiduMapNavigation.openBaiduMapWalkNavi(para, getmActivity());
        } catch (BaiduMapAppNotSupportNaviException e) {
            e.printStackTrace();

        }*/
        showInfoWindow(marker.getPosition());
        showRoteLine(marker.getPosition());
        return false;
    }

    /**
     * 地图的点击事件
     *
     * @param latLng
     */
    @Override
    public void onMapClick(LatLng latLng) {

        //关闭弹窗
        mBaiduMap.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    /**
     * 显示弹窗
     *
     * @param latLng
     */
    public void showInfoWindow(LatLng latLng) {
        //创建InfoWindow展示的view
        Button button = new Button(getmActivity());
        button.setText("弹窗");
        button.setTextColor(Color.BLACK);
        //定义用于显示该InfoWindow的坐标点
        button.setBackgroundColor(Color.WHITE);

        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, latLng, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }


    /**
     * 显示路线
     */
    private void showRoteLine(LatLng endL) {

        mSearch.setOnGetRoutePlanResultListener(listener);
        PlanNode start = PlanNode.withLocation(ll);
        PlanNode end = PlanNode.withLocation(endL);
        mSearch.walkingSearch(new WalkingRoutePlanOption()
                .from(start)
                .to(end));


    }


    /**
     * 设置标记
     */
    private void marker(double l, double a,String distance) {
        //定义Maker坐标点
        LatLng point = new LatLng(l, a);
        //构建Marker图标
        View inflate = View.inflate(getmActivity(), R.layout.layout_marker, null);
        TextView marker = (TextView) inflate.findViewById(R.id.tv_marker);
        marker.setText(distance);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(inflate);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap)
                .zIndex(4)  //设置marker所在层级
                .draggable(false);  //设置手势拖拽;

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }


    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            //获取步行线路规划结果
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //  "抱歉，未找到结果"
                return;
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {

                if (overlay == null) {
                    overlay = new WalkingRouteOverlay(mBaiduMap);
                }
                //设置可以点击
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                //overlay.zoomToSpan();
            }
        }

        public void onGetTransitRouteResult(TransitRouteResult result) {
            //获取公交换乘路径规划结果
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //获取驾车线路规划结果
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };


    /**
     * 定位的回调方法
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360  Latitude 纬度
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            //更新地图
            mBaiduMap.animateMapStatus(u);
            //停止定位
            mLocationClient.stop();



            //显示商家信息
            marker(24.50408, 118.147768,"1m");
            marker(24.50608, 118.147768,"10m");
            marker(24.50508, 118.147768,"30m");
            //获取商家信息
            getPresenter().getData(location.getLongitude(), location.getLatitude());
        }


    }


    @Override
    public void onDestroy() {
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        overlay = null;
        mSearch.destroy();
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
