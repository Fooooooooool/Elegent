package cn.itcast.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.sql.SQLException;

/**
 * Created by DELL on 2022/10/10.
 */

public class TwoFragment extends Fragment {
    //suzhoushi
    private String[] jiaoname = {"jiangnanshouxi","meilugongguan","xinmeihua","songhelou"};
    private  int []  jiaoicons = {R.drawable.jiangnanshouxi,R.drawable.meilugongguan,R.drawable.xinmeihua,R.drawable.songhelou};
    private String[] jiaobuy = {"¥150/person","¥200/person","¥70/person","¥80/person"};
    private String [] jiaointroduce = {"sucai","yuecai","yuecai","sucai"};
    private String[] jiaopingfen = {"4.5","3.8","4.0","4.1"};
    private String[] jiaotime = {"10:00-13:00,16:00-21:30","11:00-22:00","10:30-21:00","10:00-14:00,16:30-01:30"};

    //nanjingshi
    private String[] guname = {"jiangnanzao","minguohonggongguan","tongqinglou","nanjingdapaidang"};
    private  int []  guicons = {R.drawable.jiangnanzao,R.drawable.minguohonggongguan,R.drawable.tongqinglou,};
    private String[] gubuy = {"¥80/person","¥150/person","¥100/person","60/person"};
    private String [] guintroduce = {"sucai","sucai","sucai","duozhongcaixi"};
    private String[] gupingfen = {"3.4","4.0","3.2","3.5"};
    private String[] gutime = {"11:00-22:00","11:00-14:00,17:00-22:30","10:00-22:00","11:00-14:00,17:00-21:30"};

    //屏南县
    private String[] pingname = {"啊咋酒楼","壹号老宅","公园壹号中餐厅","阿纯餐馆","白水洋乡味楼酒店","熙泽路餐馆","芝根芝底披萨（屏南店）"};
    private  String[]  pingicons = {"https://img2.baidu.com/it/u=2648924671,179728156&fm=253&fmt=auto&app=138&f=JPEG?w=745&h=500",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.2m2j.com%2Fuploads%2Fgs%2Fcase%2Fdesigner00_873_09.jpg&refer=http%3A%2F%2Fwww.2m2j.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903263&t=15c6f29ba91bda3911f4333b45b8f13a",
            "https://img2.baidu.com/it/u=1211977788,2532940481&fm=253&fmt=auto&app=138&f=JPEG?w=943&h=487",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.86zsw.com%2Fpics%2F20150825114879257925.jpg&refer=http%3A%2F%2Fimg.86zsw.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903357&t=a60e687e2fce37e823734aedd7c5dc74",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdpic.tiankong.com%2F8m%2Fg5%2FQJ7123795297.jpg&refer=http%3A%2F%2Fdpic.tiankong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903394&t=f918bb9127cdb359b6a459b3b26e17b0",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zx123.cn%2FResources%2Fzx123cn%2Fuploadfile%2F2017%2F0329%2F32186372c21c0999ba5e4704cf20a8d0.jpg&refer=http%3A%2F%2Fimg.zx123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=6d521fa1e7b4b2091916266294d48317",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.to8to.com%2Fcase%2F2018%2F06%2F16%2F20180616111527-69cb78cd.jpg&refer=http%3A%2F%2Fpic.to8to.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903453&t=47f227409d0bfd7a78058d9e9fc8b577"};
    private String[] pingbuy = {"¥180/人","¥80/人","¥104/人","¥40/人","¥32/人","¥36/人","¥27/人"};
    private String [] pingintroduce = {"福建菜 屏南县中心城区","私房菜 屏南县中心城区","其他中餐 屏南县中心城区","其他美食 屏南县其他","私房菜 屏南县其他","福建菜 屏南县其他","披萨 屏南县中心城区"};
    private String[] pingpingfen = {"3.6分","3.7分","3.5分","3.6分","3.6分","3.3分","3.7分"};
    private String[] pingtime = {"11:00-22:00","11:00-14:00,16:00-22:00","暂无营业时间","11:00-14:00,17:00-22:30","10:00-22:00","11:00-14:00,17:00-21:30","10:00-次日01:30"};

    //扬州市
    private String[] zhouname = {"quyuan","yechun","yangzhouyan","yingbinguan"};
    private  int []  zhouicons = {R.drawable.quyuan,R.drawable.yechun,R.drawable.yangzhouyan,R.drawable.yingbinguan};
    private String[] zhoubuy = {"¥160/person","¥60/person","¥100/person","¥120/person"};
    private String [] zhouintroduce = {"huaiyangcai","huaiyangcai","huaiyangcai","huaiyangcai"};
    private String[] zhoupingfen = {"3.7","3.7","3.6","3.7"};
    private String[] zhoutime = {"10:00-13:30，17:00-23:30","10:30-00:30","10:30-13:30,16:30-20:30","9:00-21:00"};

    //寿宁县
    private String[] shouname = {"姑奶奶（寿宁店）","佳客来（胜利街店）","芝根芝底（寿宁店）","廊桥鱼庄（寿宁店）","沙拉斯（解放店）","重庆鸡公煲"};
    private  String[]  shouicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.qqjm.com%2F201908%2F01%2F16777d09eb9.png&refer=http%3A%2F%2Fimg.qqjm.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903571&t=9ae42e6356bd0f87bc5d2eefd89c41e9",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.to8to.com%2Fcase%2F2014%2F07%2F28%2F20140728144121-4cd559c1.jpg&refer=http%3A%2F%2Fpic2.to8to.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669904264&t=52830c16710560a187890e0f1298d675",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F12247280.s21i.faiusr.com%2F2%2FABUIABACGAAgjdeR_wUoxrqYzgEw4BI4mAs%21700x700.jpg&refer=http%3A%2F%2F12247280.s21i.faiusr.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903673&t=b89a81d3bb76f21afd90e76027f2f5ff",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/langqiaoyuzhuang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/shalasi.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/jigongbao.jpg"};
    private String[] shoubuy = {"¥43/人","¥56/人","¥26/人","¥33/人","¥34/人","¥20/人"};
    private String [] shouintroduce = {"本帮江浙菜 寿宁县中心城区","自助餐 寿宁县中线城区","披萨 寿宁县中线城区","鱼火锅 寿宁县中心城区","快餐简餐 寿宁县中心城区","川菜馆 寿宁县中心城区"};
    private String[] shoupingfen = {"3.3分","3.5分","3.6分","3.6分","3.6分","3.5分"};
    private String[] shoutime = {"10:30-13:30，16:30-20:30","08:30-24:00","09:00-24:00","10:00-24:00","08:00-24:00","11:00-24:00"};

    //福安市
    private String[] funame = {"小咖私厨工坊（福安店）","牛道","御源府餐厅（福安店）","江滨酒楼","味觉种子（福安市）","邦维执匠·咖餐甜点（甜点店）","福台源自助火锅·烧烤"};
    private  String[]  fuicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdpic.tiankong.com%2F8m%2Fg5%2FQJ7123795297.jpg&refer=http%3A%2F%2Fdpic.tiankong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=7d6def729ee3b9f345c46395659cf656",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201708%2F30%2F20170830163846_cAjfZ.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903548&t=fec2e38945f6f497bbd06b06a00da00b",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E5%AE%89%E5%B8%82/yuyuanfucanting.jpg",
            "https://img1.baidu.com/it/u=3662237316,2104661046&fm=253&fmt=auto&app=138&f=JPEG?w=857&h=500",
            "https://img2.baidu.com/it/u=1158199449,1937116701&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E5%AE%89%E5%B8%82/bangweizhijiang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E5%AE%89%E5%B8%82/futaiyuanzizhu.jpg"};
    private String[] fubuy = {"¥54/人","¥100/人","¥75/人","¥129/人","¥45/人","¥88/人","¥115/人"};
    private String [] fuintroduce = {"西餐 冠后路","潮汕牛肉火锅 福安市中心城区","福建菜 福安市中心城区","其他美食 甘棠镇","特色菜 福安市中心城区","西餐 阳头广场","自助餐 福安市中心城区"};
    private String[] fupingfen = {"3.9分","3.4分","3.7分","3.8分","3.4分","4.1分","3.4分"};
    private String[] futime = {"11:00-22:00","16:00-次日02:00","11:00-13:30,16:30-21:00","08:00-13:30,16:00-20:00","17:00-次日02:00","11:00-23:30","16:00-23:30"};

    //柘荣县
    private String[] zhename = {"晓家碧芋","聚福楼私房菜","小富春饭店","东源农家菜","乐乐锅（柘荣店）","恒昌荣饭店","九香火锅（柳城南路店）"};
    private  String[]  zheicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.shejiben.com%2Fcase%2F2016%2F02%2F15%2F20160215120845-c8cea06b.jpg&refer=http%3A%2F%2Fpic1.shejiben.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=22e328b1af7b8bd7726417446ccaaa6f",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/jufulou.jpg",
            "https://img0.baidu.com/it/u=701376769,2403496470&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=334",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50044%2F7417.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=ecc921cb50cdd571aad5b98a574f3815",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/leleguo.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/hengchangrong.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/jiuxianghuoguo.jpg"};
    private String[] zhebuy = {"¥52/人","¥37/人","¥45/人","¥33/人","¥70/人","¥70/人","¥/40人"};
    private String [] zheintroduce = {"福建 柘荣县中心城区","其他中餐 柘荣县中心城区","其他中餐 柘荣县中心城区","农家菜 柘荣县中心城区","火锅 柘荣县中心城区","家常菜 柘荣县中心城区","火锅 柘荣县中心城区"};
    private String[] zhepingfen = {"3.7分","3.7分","3.7分","3.5分","3.5分","3.5分","3.5分"};
    private String[] zhetime = {"暂无营业时间","10:00-13:00,16:00-23:00","13:30-24:00","暂无营业时间","16:30-24:00","08:30-14:00,16:00-22:00","10:00-次日01:30"};

    //福鼎市
    private String[] fudingname = {"金九龙大酒店-中餐厅","老家饭庄","闲云居","三门里逍遥林","东源大酒店","老百货自助火锅城（人本超市店）","百威火锅城（古城西路店）","山水人家休闲山庄"};
    private  String[]  fudingicons = {"https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E9%BC%8E%E5%B8%82/jinjiulongdajiudain.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E9%BC%8E%E5%B8%82/laojiafangzhuang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E9%BC%8E%E5%B8%82/xianyunju.jpg",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhappy.online.sh.cn%2Fimages%2Fattachement%2Fjpg%2Fsite1%2F20211105%2FIMG509a4c17ec4458609894080.jpg&refer=http%3A%2F%2Fhappy.online.sh.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669901853&t=b17298d1290f6de87693bf5473080002",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F8718367adab44aedb9c6fd3db11c8701a08bfb5b&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669901853&t=58b5bb1df700b36afdf9e9b15d8148b2",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E9%BC%8E%E5%B8%82/laobaihuozizhuhuoguo.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E7%A6%8F%E9%BC%8E%E5%B8%82/baiweihuoguocheng.jpg",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F1e30e924b899a9014c08a1eb28da1d7b02087af45f94&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669901853&t=34b0b4cb709fec3fcb39380b7215410f"};
    private String[] fudingbuy = {"¥100/人","¥25/人","¥40/人","¥65/人","¥162/人","¥42/人","¥47/人","¥50/人"};
    private String [] fudingintroduce = {"其他中餐 福鼎市中心城区","其他中餐 福鼎市中心城区","创意菜 福鼎市其他","其他中餐 福鼎市其他","福建菜 福鼎市中心城区","自助餐 福鼎市中心城区","火锅 福鼎中心城区","其他美食 福鼎市其他"};
    private String[] fudingpingfen = {"3.7分","3.4分","3.5分","3.4分","4.0分","3.6分","3.7分","3.7分"};
    private String[] fudingtime = {"11:00-22:00","11:00-14:00,16:00-22:00","暂无营业时间","11:00-14:00,17:00-22:30","10:00-22:00","11:00-14:00,17:00-21:30","10:00-次日01:30","10:00-次日01:30"};

    //霞浦县
    private String[] xianame = {"晨曦大酒店","环海大酒楼","鑫鑫饭店","台湾欣乐园","赵妈私房菜排挡","浦天酒店-中餐厅","人民公社（霞浦店）"};
    private  String[]  xiaicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F07%2F21%2F16pic_721042_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903992&t=7af8384f36ea861da73044ca5e9a06f1",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2Fb21bb051f81986185464465044ed2e738ad4e6c7&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669904265&t=25f2705c89378d8755f363f779e7b8f5",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.to8to.com%2Fcase%2F2016%2F12%2F25%2F20161225020123-850d8c3d.jpg&refer=http%3A%2F%2Fpic.to8to.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903836&t=9bbd5797496f1ee60d8f464cd9355e74",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fx3.tuozhe8.com%2Fattachment%2Fforum%2F201512%2F23%2F005742qxrfbvw6t8mbw6mf.jpg&refer=http%3A%2F%2Fx3.tuozhe8.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903836&t=d7ba53ef529895b2b06dd717c4f0e0ec",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/zhaomapaidang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/putianjiudian.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/renminggongshe.jpg"};
    private String[] xiabuy = {"¥230/人","¥115/人","¥58/人","¥21/人","¥115/人","¥50/人","¥70/人"};
    private String [] xiaintroduce = {"福建菜 松城镇","福建菜 松城镇","其他中餐 霞浦县其他","其他中餐 松城镇","其他中餐 三沙镇","海鲜 松城镇","湘菜 松城镇"};
    private String[] xiapingfen = {"3.5分","3.8分","3.5分","4.0分","3.4分","3.7分","3.8分"};
    private String[] xiatime = {"11:00-22:00","11:00-14:00,16:00-22:00","暂无营业时间","11:00-14:00,17:00-22:30","10:00-22:00","11:00-14:00,17:00-21:30","10:00-次日01:30"};

    private String[] name1 = {};
    private int [] icons = {};
    private  String[] buy ={};
    private  String[] introduces = {};
    private String[] pingfen = {};
    private String[] time = {};
    String name_receive1;
    int img;
    int f;
    String ziliao;
    String buy1;
    String name;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);

        Intent intent = getActivity().getIntent();
        f = intent.getIntExtra("f",0);
        name_receive1 = intent.getStringExtra("name");//景点地区
        name = intent.getStringExtra("detail_name");
        ziliao = intent.getStringExtra("detail_introduce");
        buy1 = intent.getStringExtra("detail_buy");
        img =intent.getIntExtra("detail_iv",0);

        if(name_receive1.equals("蕉城区")){
            name1 = jiaoname;
            icons = jiaoicons;
            buy =jiaobuy;
            introduces = jiaointroduce;
            pingfen = jiaopingfen;
            jiaotime = time;
        }
        if(name_receive1.equals("古田县")){
            name1 = guname;
            icons = guicons;
            buy =gubuy;
            introduces = guintroduce;
            pingfen = gupingfen;
            jiaotime = gutime;
        }
//        if(name_receive1.equals("屏南县")){
//            name1 = pingname;
//            icons = pingicons;
//            buy =pingbuy;
//            introduces = pingintroduce;
//            pingfen = pingpingfen;
//            jiaotime = pingtime;
//        }
        if(name_receive1.equals("周宁县")){
            name1 = zhouname;
            icons = zhouicons;
            buy =zhoubuy;
            introduces = zhouintroduce;
            pingfen = zhoupingfen;
            jiaotime = zhoutime;
        }
//        if(name_receive1.equals("寿宁县")){
//            name1 = shouname;
//            icons = shouicons;
//            buy =shoubuy;
//            introduces = shouintroduce;
//            pingfen = shoupingfen;
//            jiaotime = shoutime;
//        }
//        if(name_receive1.equals("福安市")){
//            name1 = funame;
//            icons = fuicons;
//            buy =fubuy;
//            introduces = fuintroduce;
//            pingfen = fupingfen;
//            jiaotime = futime;
//        }
//        if(name_receive1.equals("柘荣县")){
//            name1 = zhename;
//            icons = zheicons;
//            buy =zhebuy;
//            introduces = zheintroduce;
//            pingfen = zhepingfen;
//            jiaotime = zhetime;
//        }
//        if(name_receive1.equals("福鼎市")){
//            name1 = fudingname;
//            icons = fudingicons;
//            buy =fudingbuy;
//            introduces = fudingintroduce;
//            pingfen = fudingpingfen;
//            jiaotime = fudingtime;
//        }
//        if(name_receive1.equals("霞浦县")){
//            name1 = xianame;
//            icons = xiaicons;
//            buy =xiabuy;
//            introduces = xiaintroduce;
//            pingfen = xiapingfen;
//            jiaotime = xiatime;
//        }
        mRecyclerView = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;

    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.twofragment,parent,false));
            return holder   ;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Glide.with(getActivity()).load(icons[position]).into(holder.iv);
            holder.nameone.setText(name1[position]);
            holder.buy.setText(buy[position]);
            holder.troduce.setText(introduces[position]);
            holder.pingfen.setText(pingfen[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),MeishiActivity.class);
                    intent.putExtra("detail_iv",icons[position]);
                    intent.putExtra("detail_name1",name1[position]);
                    intent.putExtra("detail_buy",buy[position]);
                    intent.putExtra("name",name_receive1);
                    intent.putExtra("detail_introduce",introduces[position]);
                    intent.putExtra("detail_pingfen",pingfen[position]);

                    intent.putExtra("f",f);
                    intent.putExtra("image",img);
                    intent.putExtra("name1",name);
                    intent.putExtra("buy1",buy1);
                    intent.putExtra("troduce",ziliao);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }



        @Override
        public int getItemCount() {
            return name1.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView nameone;
            ImageView iv;
            TextView buy;
            TextView troduce;
            TextView pingfen;
            public MyViewHolder(View view){
                super(view);
                nameone = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                buy=(TextView)view.findViewById(R.id.buy);
                troduce =(TextView) view.findViewById(R.id.ziliao);
                pingfen = view.findViewById(R.id.pingfen);
            }
        }
    }

}
