package cn.itcast.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by DELL on 2022/8/7.
 */

public class ZhujiemianActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private RecyclerView mrecycleview;
    private HomeAdapterTwo madapter;
    String name_receive;//用于接收数据库查询的返回数据

    private String[] name = {};
    private int[] icons = {};
    private  String[] buy = {};
    private  String[] introduces = {};
    //苏州市
    private String[] suz = {"zhuozhengyuan","liuyuan","shizilin","wangshiyuan","canglangting","pingjianglu","shantangjie","xietanglaojie","suzhoubowuguan"};
    private int[] szicons = {R.drawable.zhuozhengyuan,R.drawable.liuyuan,R.drawable.shizilin,R.drawable.wangshiyuan,R.drawable.canglangting,R.drawable.pingjianglu,R.drawable.shantangjie,R.drawable.xietanglaojie,R.drawable.suzhoubowuguan};
    private String[] szbuy = {"¥80","¥55","¥40","¥40","¥20","free","free","free","free"};
    private String[] szintroduction = {"Humble Administrator's Garden covers an area of 78 mu, the whole garden is divided into three parts: east, middle and west. The east is bright and cheerful, with Pinggang distant mountains, pine forest lawn, bamboo dock and Qu Shui. The western pool is in a curved shape, which is characterized by Taiwan hall Zhi, corridor ups and downs, water reflection, unique interest, gorgeous and exquisite decoration, the main building is near the residential side of the 36 mandarin duck hall.",
    "Suzhou Liuyuan was built in the Ming Dynasty Wanli 21 years (AD 1593), for the Taimu Temple Shaoqing Xu Taishi private garden, known as the East garden, when the East garden \"Hongli Xuan, front buildings and back hall, can be drunk guests\". Ruiyun Peak \"Yan Qiao A in the south of the river\", by the stack of master Zhou Shichen stone screen, exquisite steep cut \"like a landscape painting\". Now the central pool, pool west of the lower part of the rockery stone, like the relics of the year.",
    "The Lion Forest was built from the Yuan Dynasty to the second year (1342), which is one of the representatives of Chinese classical private garden architecture. It belongs to one of the four famous gardens in Suzhou. Lion Forest is also a world cultural heritage, a national key cultural relic protection unit, and a national AAAA tourist scenic spot. Lion Forest is located in the northeast of Suzhou city. Because of the stone peaks in the park, much like a lion, the name \"lion forest\". The lion Forest is rectangular in plane, covering an area of about 15 mu. There are many and exquisite lake rockery in the forest, and the buildings are scattered at random. The main buildings are Yanyu Hall, See hill building, waterfall pavilion, ask Mei Pavilion, etc.",
    "Netmaster Garden is located at No. 11 Kuajiatou Lane, Daichengqiao Road, Southeast of Suzhou City, Jiangsu Province, about 120 kilometers from Shanghai Hongqiao Airport. Suzhou garden is a medium-sized classical landscape house representative works. The Garden was first built in the Southern Song Dynasty (AD 1127-1279). It used to be the site of the \"Wan Xuan Tang\" of Shi Zhengzhi, a scholar in Yangzhou who was a book collector and official in the Song Dynasty. The garden was named \"Yu Yin\" and was abandoned later. In the reign of Qianlong in the Qing Dynasty (about 1770 AD), Song Zongyuan, the retired Shaoqing priest of Guanglu Temple, bought it and rebuilt it, naming it \"Master of the Nets Garden\".",
    "Canglang Pavilion, located in the south of Suzhou City, Jiangsu Province, is the oldest garden in Suzhou. It was built in the Qingli period of the Northern Song Dynasty (1041 ~ 1048), and was once the residence of the famous general Han Shizhong in the early Southern Song Dynasty (early 12th century). Canglang Pavilion garden art is unique, not into the garden door will be set a pool of green water around the garden. The park with rocks as the main scene, face a mountain, canglang stone pavilion is located on it. There is a pool chiseled under the mountain, and a winding corridor connects the mountains and rivers. The Ming Dao Hall in the southeast of the rockery is the main building of the garden. In addition, there are five hundred sages' ancestral Hall, hill tower, Cui Linglong Hall, Yang Zhi Pavilion and Imperial Stinscription Pavilion and other buildings set off it.",
    "Pingjiang Road is an old historical street in Suzhou. It is a path along the river, which is called Pingjiang River. Pingjiang Road Historical District is the most well-preserved area of Suzhou ancient city, which can be called the epitomization of the ancient city. Compared with \"Pingjiang Map [1]\" in the Southern Song Dynasty and \"General Map of Waterways in Suzhou Fucheng\" in the late Ming Dynasty, Pingjiang Road basically continued the pattern of city and square since the Tang and Song Dynasties, and maintained vitality. Pingjiang Road south from Ganjiang East Road, North Yue Baita East Road and North East Street, the ancient name is called \\\" Shiquanli \\\", earlier appeared in the 1834 \"Wu Men Table hidden\", said :\\\" Pingjiang Road ancient name of Shiquanli, there are ten ancient Wells, Huayang Bridge south one, Xijia Bridge south one, Yuan Bridge north one \\\", very detailed. Pingjiang Road is a road along the river, with a total length of about 1,606 meters, that is, three li long, and there are many horizontal streets and narrow lanes on both sides, such as Lion Temple Lane, Chuanfang Lane, East flower bridge lane, Caohuxu Lane, Daxinqiao Lane, Weidao Guanqian, Zhongzhangjia Lane, Daru Lane, Lilac Lane, Huxiangzhi Lane, Xiaojia Lane, Nianjia Lane, hanging bridge lane and so on.",
    "Shantang Street is located in the northwest of the ancient city of Suzhou, Jiangsu Province. In the east, it goes to the gate of Chang, \"a place of wealth and elegance\" in the world, and in the west, it goes to Huqiu, \"the first scenic spot in Wuzhong\". The total length of Shantang Street is about 3,600 meters, about 7 li, so it is called \"seven Li Shantang to Huqiu\". Shantang Street is named after the Shantang River. In the first year of the Tang Dynasty (825), the poet Bai Juyi served as the governor of Suzhou. He dredged the northwest river outside the city of Suzhou and used the natural river to dig into the straight river (from the moat outside the gate of Chang to the foot of the Tiger Hill), called Shantang River, which was convenient for sailing boats. At that time, Tiger Hill was called Wuqiu, and there was Wuqiu Temple on the mountain. Therefore, the poet wrote it as Wuqiu Temple Road, and later called \"White public Dike\". The dike was seven miles long, and it was also called seven miles Mountain Pond. Because it is between the downtown of Chang Men and the scenic spot Huqiu, it has gradually become a bustling tourist commercial street.",
    "Xitang Old Street is located at the Xitang River in the east of Jinji Lake in Suzhou Industrial Park. It is a commercial district featuring cultural tourism, with strong memories of Jiangnan water home and humanistic feelings. It was rebuilt on the site of Xitang Town with a history of more than 760 years. At the beginning of the planning, the historical evolution and local customs of Xitang Town were deeply excavated. Through the planning and design of antique architecture, Soviet-style gardens, green gardening, leisure business and other aspects, the traditional culture and modern commerce were organicly integrated. To build a collection of catering, shopping, entertainment, culture and other functions as one of the fashion leisure block.",
    "Suzhou Museum, located at No. 204, Northeast Street, Gusu District, Suzhou City, was established on January 1, 1960, and the site is the Taiping Emperor's Palace. On October 6, 2006, the Suzhou Museum designed by I. M. Pei was completed and officially opened to the public. The museum covers an area of about 10700 square meters, with a floor area of more than 19000 square meters, and a total floor area of 26500 square meters, including the Palace of the Taiping Heavenly Kingdom. On September 25, 2021, the West Hall of Suzhou Museum opened for trial operation, with a construction area of 48,365 square meters and an exhibition area of 13,391 square meters. Suzhou Museum is a local comprehensive museum that collects, displays, studies and spreads the history, culture and art of Suzhou. Suzhou Museum has four basic exhibitions: Wu-land relics, Wu-ta National treasures, Wu-zhong elegance and Wu-men calligraphy and painting. The total number of collections is 24819 pieces/set, and 9734 pieces/set of precious cultural relics, of which 222 pieces/set of first-class items and 829 pieces/set of second-class items. There are 8683 pieces/set of third-level products, which are good at archaeological relics unearthed over the years, Ming and Qing paintings and crafts."};
    //南京市
    private  String [] nanj = {"fuzimiao","xuanwuhu","zhongshanling","zhenzhuquan","nanjingzongtongfu","mingxiaoling","nanjingbowuguan","jimingsi","zhanyuan","yuhuatai"};
    private int[] njicons = {R.drawable.fuzimiao,R.drawable.xuanwuhu,R.drawable.zhongshanling,R.drawable.zhenzhuquan,R.drawable.zongtongfu,R.drawable.mingxiaoling,R.drawable.nanjingbowuguan,R.drawable.jimingsi,R.drawable.zhanyuan,R.drawable.yuhuatai};
    private String[] njbuy = {"¥30","¥20","free","¥40","free","free","¥25","¥10","¥100","free"};
    private String[] njintroduction = {"Nanjing Confucius Temple is located in the west of Gongyuan Street and Jiangnan Gongyuan on the north bank of Qinhuai River in Qinhuai District of Nanjing. It is located in the core area of Qinhuai River scenery belt of Confucius Temple, namely Nanjing Confucius Temple, Nanjing Confucian Temple and Wenxuanwang Temple. It is the place where Confucius is worshipped and offered sacrifices. It is not only the cultural and educational center of Nanjing in the Ming and Qing Dynasties, but also the cultural and educational complex of the crown in the Southeast provinces. Confucius Temple is a group of large-scale ancient architectural complex, mainly composed of Confucian temple, academy, tribute courtyard three buildings, covering a large area. There are Zhaobei, Panchi, archway, Juxing Pavilion, Kuixing Pavilion, Lingxing gate, Dacheng Hall, Mingde Hall, Zunjing Pavilion and other buildings. The Confucius Temple is known as a scenic spot in the Qinhuai region and a characteristic landscape area in the ancient capital of Nanjing. From the Six Dynasties to the Ming and Qing Dynasties, many families gathered nearby, so it is known as the \"Six Dynasties of golden fans\". It is the largest traditional ancient street market in China, and is one of the four major downtown markets in China, along with the Shanghai Chenghuang Temple, Suzhou Xuanmiao Temple and Beijing Tianqiao. It is also a famous open national AAAAA tourist attraction and international tourist attraction in China.",
            "Xuanwu Lake is located in Xuanwu District, Nanjing City, Jiangsu Province, with Zijinshan Mountain in the east, Ming City Wall in the west, Nanjing Railway Station in the north, and Zhoushan Mountain in the south. It is the largest urban park in the south of the Yangtze River, the largest royal garden lake in China, and the only royal garden in the south of the Yangtze River. It is known as the \"Pearl of the Jinling\", also known as the Back Lake and the North Lake. The cultural history of Xuanwu Lake can be traced back to the pre-Qin period. During the Six Dynasties, it became a place for the emperor to read the sailors and was turned into a royal garden. On the south bank, there were royal palaces such as Hualin Garden and Leyou Garden. In the Northern Song Dynasty, Wang Anshi, the governor of Jiangning Prefecture, \"returned the abandoned lake to the field\", and the Xuanwu Lake disappeared for more than 200 years. In the Yuan Dynasty, after two dredging, Xuanwu Lake reappeared; In the Ming Dynasty, it was set up as the Houhu Yellow Book Library, which was the royal forbidden land. At the end of the Qing Dynasty, when the Nanyang persuasion meeting was held, Fenrun Gate (now Xuanwu Gate) was opened, and Xuanwu Lake became a tourist area. In August 1928, Xuanwu Lake was officially opened to the public as a park." ,
            "Sun Yat-sen's Mausoleum is located in Zhongshan Mountain Scenic Spot, south of Zijinshan Mountain, Xuanwu District, Nanjing City, Jiangsu Province. It is the mausoleum of Sun Yat-sen, the great pioneer of modern China's democratic revolution, and its affiliated memorial buildings. The mausoleum covers an area of more than 80,000 square meters, and was started in the spring of 1926 and completed in the summer of 1929. With Pingchuan in front, Qingzhang in the back, Linggu Temple in the east and Ming Xiaoling Mausoleum in the west, the whole architectural complex is built along the mountain and gradually rises along the central axis from south to north. The main buildings are fraternity Square, tomb road, tomb gate, stone steps, stately pavilion, worship hall and tomb chamber, etc., arranged on a central axis, which reflects the style of traditional Chinese architecture. Like a \"Liberty bell\" lying flat on a green carpet. The architecture of Sun Yat-sen's mausoleum combines the essence of ancient Chinese and Western architecture, solemn and simple, and does not innovate.",
            "The Pearl Spring Scenic Area is located at the south foot of Dingshanxi, Pukou District, Nanjing City, Jiangsu Province. It is about 7 kilometers away from Pukou Railway Station and Pukou Ferry, and 16 kilometers away from Nanjing city. Pearl Spring was reconstructed as a scenic park in May 1984, with a total area of 14.8 square kilometers, including 1 square kilometer of water area. The annual reception of the scenic spot reaches 1.2 million people, and the maximum daily reception is 91,113 people. Nanjing Pearl Spring Scenic Spot is now a national water Conservancy Scenic spot, national AAAA level tourist scenic spot, Jiangsu Provincial tourist resort, Jiangsu provincial park.",
            "Nanjing President's Office is located at No. 292, Changjiang Road, Xuanwu District, Nanjing City. It is the largest and best preserved architectural complex in modern Chinese architectural remains, and is also one of the main representatives of Nanjing Republic of China architecture and an important site of modern Chinese history. Since modern times, the President's Office in Nanjing has become the center of China's political and military affairs and the source of major events. A series of major events in China may take place here, which is closely related to it. Many important figures have been active here. The Nanjing Presidential Palace has a history of more than 600 years, which can be traced back to the GUI De Hou Mansion and the Han Wang Mansion in the early Ming Dynasty. In the Qing Dynasty, it was established as Jiangning Weaving Department, Liangjiang Governor's Department, etc., and Kangxi, Qianlong's southern tour took this as the palace. After the Taiping Heavenly Kingdom made Tianjing its capital, it built a grand Tianwang mansion here. On January 1, 1912, Sun Yat-sen was sworn in as the provisional President of the Republic of China, which was turned into the Grand presidential office and later the presidential office of the Nanjing National Government.",
            "Ming Xiaoling Mausoleum is located at the south foot of Zijinshan Mountain in Xuanwu District, Nanjing City, Jiangsu Province, under the Dulongfu play Mount Qomolangma, east of Zhongshan's tomb, south of Meihua Mountain, located in the Zhongshan Mountain Scenic Spot, is the tomb of Ming Taizu Zhu Yuanzhang and his queen. Because the Empress Ma Shi posthumous title \"Filial piety high Empress\", and because the practice of filial piety rule the world, the name \"Xiaoling\". Covering an area of more than 1.7 million square meters, it is one of the largest imperial tombs in China. Ming Xiaoling was built in the fourteenth year of Ming Hongwu (1381), and completed in the third year of Ming Yongle (1405). It has called 100,000 military industries, which lasted for 25 years. Tang Song Emperor mausoleum \"according to the mountain for the tomb\" old system, and create square grave for Circular mound new system. The harmonious unification of humanity and nature achieves the perfect height of the integration of nature and man, and becomes an excellent model of the combination of traditional Chinese architectural art and culture and environmental aesthetics. The main buildings and stone carvings in the mausoleum area include Square city, Ming tower, Bao city and Bao Ding, including Xia Ma Fang, Great Golden Gate, Shen Gong Sheng De tablet, Shen Tao, stone statue Road stone carvings, etc., which are all architectural remains of the Ming Dynasty and maintain the authenticity of the original building and the integrity of the spatial layout of the mausoleum.",
            "Nanjing Museum is located at No. 321, Zhongshan East Road, Xuanwu District, Nanjing City, Jiangsu Province. It is one of the three major museums in China, referred to as South Yuan or South Bo. Its predecessor was the National Central Museum, which was founded by CAI Yuanpei and others in 1933. Now it is a large-scale comprehensive national museum, the first batch of national first-class museums, the first batch of central and local co-built national museums, the national AAAA tourist attractions, the national key cultural relic protection units and China's 20th century architectural heritage. Nanjing Museum covers an area of more than 130,000 square meters, with a pattern of \"one hospital and six museums\", namely the history museum, special exhibition hall, digital museum, art museum, intangible cultural heritage museum, and the Republic of China Museum. In addition, the Institute has \"six\" research departments, namely, the Institute of Archaeology, the Institute of Cultural Relic Protection, the Institute of Ancient Architecture, the Institute of Exhibition Art, the Institute of Intangible Cultural heritage Protection, and the Institute of Ancient Art. It also has the only research institution of ethnic folklore in the Chinese museum, among which the Institute of Cultural Relic protection is called \"the third grade Hospital of cultural Relic\". It is the key scientific research base of the State Administration of Cultural Heritage of China's Paper Cultural relics protection.",
            "Jiming Temple is located in the east foot of Jilong Mountain, Xuanwu District, Nanjing, also known as the ancient Jiming Temple. It was built in the first year of Yongkang in the West Jin Dynasty (300 years), and has a history of more than 1,700 years. It is one of the oldest Sanskrit temples and royal temples in Nanjing, and its incense has always been vigorous. The first of the \"four hundred and eighty temples in the Southern Dynasty\", it was the center of Buddhism in China during the Southern Dynasty, along with Qixia Temple and Dingshan Temple. The history of Jiming Temple can be traced back to Qixuan Temple in the Eastern Wu Dynasty, which was located in the back garden of the Wu State during The Three Kingdoms. In the first year of Yongkang in the West Jin Dynasty (300 years), he built a room against the mountain and created the ashram. After the Eastern Jin Dynasty, this place was opened as the Tingwei Department; In the first year of the Southern Liang Dynasty (527), Emperor Wudi of Liang built Tongtai Temple in Jimingdai. He sacrificed his life here four times and promulgated the Text on Meat and wine, which was the beginning of Buddhist vegetarian diet. This place became a Buddhist resort. In the Southern Tang Dynasty, it was renamed Jingju Temple, and later changed to Yuanji Temple. In the Song Dynasty, it was changed to the Magic Treasure Temple.",
            "Zhanyuan is located in the core area of Qinhuai Scenery Belt of Confucius Temple in Qinhuai District of Nanjing City, Jiangsu Province. It is the oldest extant classical garden of Ming Dynasty in Nanjing and one of the \"four famous Gardens in southern China\". Its history can be traced back to the Ming Taizu Zhu Yuanzhang Emperor before the Wu Palace, after the King of Zhongshan Xu Da's palace garden, known as rockery, Ouyang Xiu poem \"looking at the jade Hall, such as in the sky\" named, Ming Dynasty is known as the \"Southern capital first garden\". It is now a national key cultural relic protection unit and a national AAAAA tourist scenic spot. Covering an area of about 20,000 square meters, Zhanyuan has more than 20 scenic spots, both large and small. The layout is elegant and exquisite, including magnificent ancient buildings of Ming and Qing Dynasties, steep rockery mountains, the famous Taihu Stone of the Northern Song Dynasty, quiet and simple pavilions and pavilions, and marvelous peaks. There is the Taiping Heavenly Kingdom History Museum in the garden, which is the only Taiping Heavenly Kingdom history museum in China. In the 87 edition of A Dream of Red Mansions and Zhao Yazhi's edition of The Legend of the New White Lady, the White Mansion was shot in Zhanyuan.",
            "Yuhuatai Martyrs' Cemetery, located one kilometer outside the Gate of China in the south of Nanjing, Jiangsu Province, covers an area of 153.7 hectares. It is the earliest and largest national martyrs' cemetery established after the founding of New China. On the central axis of 1500 meters in length, from north to south, martyrs martyred group sculptures, martyrs monument, reflecting pool, memorial bridge, memorial hall, and Zhongsoul Pavilion are unfolded in turn to form the central memorial area, which is solemn and majestic. Complementing the central memorial area, there are also well-known martyrs' tombs and memorial buildings such as the East and West martyr sites distributed throughout the cemetery.\n" +
                    "\n"};
    //扬州市
    private  String [] yangz = {"shouxihu","damingsi","geyuan","heyuan","dongguanjie","guazhougudu","hanlingyuan","guyunhe","mengchengyi","shaoboguzhen"};
    private int[] yzicons = {R.drawable.shouxihu,R.drawable.damingsi,R.drawable.geyuan,R.drawable.heyuan,R.drawable.dongguanjie,R.drawable.guzhougudu,R.drawable.hanlingyuan,R.drawable.guyunhe,R.drawable.mengchengyi,R.drawable.shaoboguzhen};
    private String[] yzbuy = {"¥100","¥30","¥135","¥45","free","free","¥30","free","free","free"};
    private String[] yzintroduction = {"Slender West Lake, formerly known as Guarantee Lake, is a tributary of the Yangzhou section of the Beijing-Hangzhou Grand Canal. It is located at No. 28, Dahongqiao Road, Hanjiang District, Yangzhou City, Jiangsu Province, with a water area of 700 mu. Slender West Lake is made up of the ancient Sui and Tang Grand Canal system and the moat of the Sui, Tang, Song, Yuan, Ming and Qing dynasties. Slender West Lake in the Qing Dynasty Kangqian period has formed a basic pattern. Slender West Lake is a typical small shallow lake, and its source water comes from the Beijing-Hangzhou Grand Canal.",
            "Daming Temple, located in the northwest suburb of Yangzhou City, Jiangsu province, was approved by The State Council to be included in the sixth batch of national key cultural relic protection units list. Daming Temple was originally built in the Southern Song Dynasty during the reign of Emperor Xiaowu and Daming (457-464). In the Qing Dynasty, because of the taboo \"Daming\" two characters, was once along the \"Qiling temple\", Qianlong 30 years of the emperor's handwriting book \"rech the title of the law of the net temple\". In 1980, Daming Temple restored its original name. In 2002, it was rated as the national AAAA level scenic spot. According to the History of the Old Five Dynasties, in 887, Yang Xingmi, the governor of Luzhou, set up camp here during the war with Qin Yan, the governor of Xuanzhou." ,
            "The park is located in the northeast corner of Guangling District, Yangzhou City, Jiangsu Province, No. 10 Yanfu East Road, has won the third batch of \"National Key Cultural Relic Protection Units\" and \"the first batch of National Key parks\" title. This private garden of Yangzhou salt merchant's mansion in Qing Dynasty is famous for planting green bamboo everywhere and winning with rockery in spring, summer, autumn and winter. It was built as a residential garden on the basis of the \"Shouzhi Garden\" of the Ming Dynasty by Huang Zhijun, the general manager of the salt industry of the Lianghuai Province, in the 23rd year of Jiaqing (1818 AD). The garden is famous for its stone folding art. The rockery in spring, summer, autumn and winter is made of bamboo shoot stone, lake stone, Huangshi and Xuanshi, which integrates the principles of garden building and landscape painting, and is praised as \"the only example in China\" by the garden master Mr. Chen Congzhou.",
            "He Garden, located at No. 66, Xuningmen Street, Yangzhou City, Jiangsu Province, also known as \"Post Xiao Villa\", is a Chinese classical garden architecture built in the mid-Qing Dynasty, known as the \"first garden of the late Qing Dynasty\", with an area of more than 14,000 square meters and a construction area of more than 7,000 square meters. The garden is made around the world by He Zhi in the Guangxu period of Qing Dynasty. The Shishan House is a masterpiece of master Shi Tao. He Yuan is a national key cultural relic protection unit, national AAAA level tourist scenic spot, one of the first 20 key parks in the country, the main attractions are complex corridor, horse riding building, etc. He Garden is a representative work of Yangzhou gardens in the late Qing Dynasty, which is a national key cultural relic protection unit.",
            "Dongguan Street is one of the most representative historical streets in Yangzhou. It reaches the ancient canal in the east and the National Day Road in the west, with a total length of 1122 meters. The original street pavement is paved with long slabs and stones. This street was not only the main water and land traffic of Yangzhou before, but also the center of commerce, handicrafts and religious culture. The street is bustling with businesses and businesses, and business is booming. Lu Chen line, oil rice square, fresh fish line, eight fresh line, melon and fruit line, bamboo line nearly a hundred.",
            "Guazhou Ancient Ferry is located at the intersection of Yangzhou Grand Canal and Yangtze River in the lower reaches of Yangzhou City, Jiangsu Province. Runyang Highway Bridge, Zhenyang Ferry and Yangzhou Port are adjacent to it, and Zhenjiang Jinshan Temple is opposite to the park across the river. \"Bian flow, Si flow, flow to Guazhou ancient ferry\", \"Jingkou Guazhou one water\". Jian Zhen, a famous monk in the Tang Dynasty, set sail from here to Japan, and Emperor Kangqian and poet Moke of all dynasties passed through Guazhou, leaving behind many popular poems. The story of Du Shiniang's angry sinking of treasure boxes happened here.",
            "Yangzhou Hanling Garden, also known as the Han Guangling King's Tomb Museum, is the first Western Han Dynasty tomb excavated in 1979 in Gaoyou City Gaoyou Lake West New District Shenju Mountain, the area of its wood zi is 18 times larger than the Hunan Mawangdui Han tomb, representing the highest etiquette of the ancient funeral \"yellow intestinal scum\", and unearthed Jin Lu jade clothing fragments. Preliminary research indicates that the owner of the tomb is Liu Xu and his wife, King of Guangling in the Western Han Dynasty. Hanling Garden is one of the important tourist attractions in the Slender West Lake and Shugang Scenic spot, and it is a pearl in the scenic spot. It is AN exhibition center of CULTURAL relics and gardens, reflecting Yangzhou Han CULTURE, with undulating terrain, simple and powerful buildings, lush trees and green grass. It is a national AAAA scenic spot.",
            "The Grand Canal is a great project on the eastern plain of China. It is a great water conservancy construction created by the ancient working people of China. It is the longest canal in the world, and also the earliest and largest canal in the world. Founded in 486 BC, the Grand Canal consists of three parts: the Grand Canal of Sui and Tang Dynasties, the Grand Canal of Beijing-Hangzhou and the Grand Canal of Eastern Zhejiang. It is 2,700km long and spans more than 10 latitudes of the earth, covering Beijing, Tianjin, Hebei, Shandong, Henan, Anhui, Jiangsu and Zhejiang provinces and municipalities directly under the Central government, running through the North China Plain. Connecting the five major river systems of the Hai River, the Yellow River, the Huaihe River, the Yangtze River and the Qiantang River, the Grand Canal is the main artery of North-South communication in ancient China. By 2020, the Grand Canal has a history of more than 2,500 years.",
            "Yucheng Post, located in Guanyi Lane, Nanmen Street, Gaoyou City, Yangzhou City, Jiangsu Province, was built in the eighth year of Hongwu of Ming Dynasty (1375). It is the largest and best preserved ancient post station in China. In the second year of Longqing (1568), Zhao Laiheng, the governor of the state, rebuilt according to the old system. Since then, the governor Zhang Desheng, Feng Xin, Zhu Ronggui and other people have rebuilt or added. It covers an area of more than 16,000 square meters. There were more than 100 halls, including post station, post house, Qin post mansion, post house, military attache hall, horse temple, horse barn, warehouse, prison room, post dormitory and other buildings. Huanghua Hall and Resident Hall are the main buildings of Yucheng Yi. In the eighth year of Hongwu of Ming Dynasty (1375), Huang Keming, the governor of the state, built Yucheng Post at the south gate of Gaoyou City. Ming Yongle first year (1403), Zhizhou Wang Jun rebuilt. In the 36th year of Jiajing (1557), the Japanese pirates invaded the country, and the Yucheng Post was destroyed in the fire of war. In the second year of Longqing (1568), Zhao Laiheng, the governor of the state, rebuilt according to the old system. Since then, the governor Zhang Desheng, Feng Xin, Zhu Ronggui and other people have rebuilt or added. After the Revolution of 1911, the Yucheng Post was ordered to be withdrawn. After the founding of the People's Republic of China, Yucheng Post was used as a residential residence. In 1993, Gaoyou Municipal People's Government presided over the renovation and repaired the main building of the post station, forming a beautiful Ming and Qing residential complex together with the Nanmen Ancient Street. In 2003, the State Post Bureau established Yucheng Post as the \"National patriotic Education base for postal workers\", and the State Administration of Cultural Heritage confirmed the Post Museum as one of the 100 national characteristic museums.",
            "Shaobo ancient town, is one of thousands of historical and cultural towns, has a history of more than 1600 years. In 2008 Shaobo was included in the fourth batch of Chinese historical and cultural town list. In 2014, Shaobo Ancient Road of Ming and Qing Canal, Shaobo Wharf group, Shaobo ancient Dike, Shaobo old lock and main line of Huaiyang Canal (Shaobo section) were included in the World Heritage Protection list of the Grand Canal of China. Shaobo Ancient town is the ancient town with the most heritage points along the Grand Canal."};

    //杭州市
    private String[] hangz = {"西湖","雷峰塔","白堤断桥","灵隐寺","千岛湖","宋城","九溪十八涧","西溪湿地"};
    private int[] hzicons = {R.drawable.liyuxi,R.drawable.jiulongfengjingmingshengqu,R.drawable.chenxiaogucun,R.drawable.zhouningdishuiyan,R.drawable.zudian,R.drawable.zhouningbanruosi,R.drawable.jiulongshiku,R.drawable.gaoshanmingzhu,R.drawable.puyuanzongsi};
    private String[] hzbuy = {"免费","¥40","免费","¥75","¥195","¥300","免费","免费"};
    private String[] hzintroduction = {"西湖，又名钱塘湖，位于中国浙江省杭州市西湖区龙井路1号，杭州市区西部，汇水面积为21.22平方千米，湖面面积为6.38平方千米。西湖南、西、北三面环山，东邻城区，南部和钱塘江隔山相邻，湖中白堤、苏堤、杨公堤、赵公堤将湖面分割成若干水面，湖中有三岛，西湖群山以西湖为中心，由近及远可分为四个层次。西湖流域内年径流量为1400万立方米，蓄水量近1400万立方米。 [西湖是一座设备齐全、管理完善的灌溉济运水库，通过河渠和闸门，为杭州市区及周边农田提供了充足的水源。西湖还可以协助调节钱塘江的水位，防止城市内涝。",
            "雷峰塔，初名黄妃塔，又名西关砖塔，位于浙江省杭州市西湖区，地处西湖风景区南岸夕照山之上，是吴越国王钱俶为供奉佛螺髻发舍利、祈求国泰民安而建，始建于北宋太平兴国二年（977年），历代屡加重修，现存建筑以原雷峰塔为原型设计，重建于2002年；是“西湖十景”之一、中国九大名塔之一，为中国首座彩色铜雕宝塔。雷峰塔主体为平面八角形体仿唐宋楼阁式塔，各层盖铜瓦，转角处设铜斗拱，飞檐翘角，总高71.679米，总占地面积3133平方米，总建筑面积为6089平方米；塔身对径28米，边长11米，周长88米，塔底为原雷峰塔遗址。",
            "西湖断桥位于杭州北里湖和外西湖的分水点上，一端跨着北山路，另一端接通白堤。据说，早在唐朝断桥就已经建成，宋代称保佑桥，元代称段家桥。在西湖古今诸多大小桥梁中，它的名气最大。当代诗词大家厉声教曾留下《断桥春草》一诗。断桥之名得于唐朝。其名由来，一说孤山之路到此而断，故名；一说段家桥简称段桥，谐音为断桥；中国民间爱情传说《白蛇传》的故事即发生于此。传说白娘子与许仙断桥相会，确为断桥景物增添了浪漫色彩。现存断桥是1941年改建，20世纪50年代又经修饰。桥的东北有碑亭，内立“断桥残雪”碑。断桥残雪为西湖十景之一。断桥位于白堤东端，南宋时又名宝祐桥，也称为段家桥。今桥为1941年重建。桥畔有“云水光中”水榭和断桥残雪碑亭。断桥残雪景观内涵说法不一，一般指冬日雪后，桥的阳面冰雪消融，但阴面仍有残雪似银，从高处眺望，桥似断非断。伫立桥头，放眼四望，远山近水，尽收眼底，是欣赏西湖雪景之佳地。 五十年代又经修饰 。桥的东北有碑亭，内立“断桥残雪”碑。",
            "灵隐寺，中国佛教古寺，又名云林寺，位于浙江省杭州市，背靠北高峰，面朝飞来峰，始建于东晋咸和元年（326年），占地面积约87000平方米。灵隐寺开山祖师为西印度僧人慧理和尚。南朝梁武帝赐田并扩建。五代吴越王钱镠命请永明延寿大师重兴开拓，并赐名灵隐新寺。宋宁宗嘉定年间，灵隐寺被誉为江南禅宗“五山”之一。清顺治年间，禅宗巨匠具德和尚住持灵隐，筹资重建，仅建殿堂时间就前后历十八年之久，其规模之宏伟跃居“东南之冠” 。清康熙二十八年（1689年），康熙帝南巡时，赐名 “云林禅寺”。",
            "千岛湖，即新安江水库，位于浙江省杭州市淳安县境内，小部分连接杭州市建德市西北，是为建新安江水电站拦蓄新安江上游而成的人工湖。新安江水库建成后，大坝将新安江上游拦截成一个巨大的湖泊。崇山峻岭淹入湖中成为大小岛屿，共1078个，故名“千岛湖”。千岛湖分东南湖区、中心湖区、西北湖区、西南湖区、东北湖区、和石林景区六个游区。1955年，新安江水库动工兴建。1959年，新安江水电站封孔蓄水。1960年，新安江水库建成。1984年12月15日，浙江省地名委员会正式将新安江水库命名为“千岛湖”。千岛湖为“世界三大千岛湖”之一。千岛湖水库坝高105米，长462米；水库长约150千米，最宽处达10余千米；最深处达100余米，平均水深30.44米，在正常水位情况下，面积约580平方千米，蓄水量可达178亿立方米，在最高水位时拥有1078座大于0.25平方千米的陆桥岛屿，并以2平方千米以下的小岛为主，岛屿面积共409平方千米。",
            "宋城（Song Cheng），位于浙江省杭州市之江路148号，杭州之江旅游度假区内，1996年5月18日开业，是杭州市第一个大型人造主题公园。宋城以“建筑为形，文化为魂”为经营理念，仿宋代风格建造，主体建筑依据北宋画家张择端的长卷《清明上河图》而建，并按照宋书《营造法式》建造，还原了宋代都市风貌，是杭州市第一个反映两宋文化内涵的主题公园，年接待游客超过1000万。大型歌舞《宋城千古情》是宋城的灵魂，与拉斯维加斯的O秀、巴黎红磨坊并称“世界三大名秀”。",
            "九溪十八涧景色天然，少有匠气，“溪水因山而曲折”，忽左忽右，其中6处溪流漫过山径，上置石步，游人可涉水溪中，也可自石上鹤步而过，各有意趣。身历其 境，尘襟为之一洗，更有一种人在画中游的感觉。 九溪之水发源于杨梅岭，途中汇合了青湾、宏法、方家、佛石、百丈、唐家、小康、云栖、渚头的溪流，因称九溪，溪水一路上穿越青山翠谷，又汇集了无数细流， 所以称九溪十八涧。",
            "浙江杭州西溪国家湿地公园，位于浙江省杭州市西湖区和余杭区交界处，距西湖不到5千米，规划总面积11.5平方千米，湿地内河流总长100多千米，约70%的面积为河港、池塘、湖漾、沼泽等水域。湿地公园内生态资源丰富、自然景观幽雅、文化积淀深厚，与西湖、西泠并称杭州“三西”。是中国第一个集城市湿地、农耕湿地、文化湿地于一体的国家级湿地公园。2009年7月7日，浙江杭州西溪国家湿地公园被录入国际重要湿地名录。2012年1月11日，杭州西溪湿地旅游区被正式授予“国家5A级旅游景区”称号，成为中国首个国家5A级景区的国家湿地公园。2013年10月31日被中央电视台评选为中国“十大魅力湿地”。",
            };
    //重庆市
    private String[] chongq = {"重庆大足石刻景区","重庆巫山小三峡","重庆武隆喀斯特旅游区","重庆酉阳桃花源景区","重庆万盛黑山谷景区","重庆南川金佛山景区","重庆江津四面山景区","重庆云阳龙缸景区","重庆彭水阿依河景区"};
    private int[] cqicons = {R.drawable.xixi,R.drawable.xipu,R.drawable.lingfeng,R.drawable.nanshan,R.drawable.xiadangcun,R.drawable.shuikufengjingqu,R.drawable.shengtaichayuan,R.drawable.xi,R.drawable.mindongjiuzhi,R.drawable.chelinggudao};
    private String[] cqbuy = {"¥40","¥60","¥80","¥40","¥40","¥50","¥40","¥20","¥30"};
    private String[] cqintroduction = {"大足石刻（Dazu Rock Carvings）位于重庆市大足区境内，是唐、五代、宋时所凿造，明、清两代亦续有开凿。现为世界文化遗产，世界八大石窟之一。大足石刻代表了公元9—13世纪世界石窟艺术的最高水平，是人类石窟艺术史上最后的丰碑。它从不同侧面展示了唐、宋时期中国石窟艺术风格的重大发展和变化，具有前期石窟不可替代的历史、艺术、科学价值。并以规模宏大、雕刻精美、题材多样、内涵丰富、保存完好而著称于世。",
            "小三峡，全称巫山小三峡·小小三峡景区，位于重庆市巫山县巫峡镇宁江路。巫山小三峡由大宁河下游流经巫山境内的龙门峡、巴雾峡、滴翠峡组成，与长江大三峡毗邻。巫山小小三峡位于大宁河滴翠峡处的支流马渡河上，是长滩峡、秦王峡、三撑峡的总称，小小三峡是大宁河小三峡的姊妹峡，因比大宁河小三峡更小，故名“小小三峡”。巫山小三峡·小小三峡景区全长65千米。有水峡景观、峰岩景观、洞穴景观、动植物景观、人文景观、空间景观。其中水体景观包括水、泉、溪、潭、滩、帘瀑、卵石等多种类型，峰岩景观包括峭壁、山峰、奇石、图案、肌理、色彩等多种类型，洞穴景观包括发育程度各异、大小各异、高度各异、风格各异的众多喀斯特溶洞、地下河。",
            "武隆喀斯特旅游区位于重庆市武隆区境内，拥有罕见的喀斯特自然景观，包括溶洞、天坑、地缝、峡谷、峰丛、高山草原等，形态全面；兼具丰富多彩的度假、休闲、娱乐、运动项目，以及土家族、苗族、仡佬族等少数民族独特的民俗风情。2011年，它被评为国家AAAAA级旅游区。武隆喀斯特旅游区包括重庆武隆旅游景点天生三桥、仙女山、芙蓉洞这三部分。",
            "酉阳桃花源景区，位于重庆市酉阳土家族苗族自治县境内桃花源路232号，东邻湖南省龙山县，南与秀山县及贵州省松桃、印江县接壤，西与贵州省沿河县隔江（乌江）相望，西北与彭水县，正北与黔江区及湖北省咸丰、来凤县相连。酉阳桃花源景区总面积50平方千米，由古桃源、太古洞、酉州古城、桃花源森林公园、桃花源广场、桃花源风情小镇、二酉山世外桃源文化主题公园、桃源大舞台等八部分组成。集岩溶地质奇观、秦晋农耕文化、土家民俗文化、自然生态文化于一体，浓缩了中国武陵山区最美的原生态自然田园风光，传承着土家族、苗族悠久的人文历史与灿烂的民族文化。",
            "黑山国家森林公园位于重庆市东南部，东经106,45,06，至1070,03,21，北纬280,452,9006,48，海拔最低700 米，最高1973 米，面积26 . 52 平方公里，包括黑山、黑山谷、狮子槽、沙坝和楠木沟五个片区。公园以丰富多样的地貌景观和山林植被及天象为特色，具有幽、静、险、狭、秀的特点，因为受外界破坏较少，许多原始建筑与植被保存完好，茂密的森林与幽豁的山形地貌，加上景区内的古夜郎国文化遗址与少数民族生活习俗，为山林增添了神秘色彩。",
            "重庆金佛山国家级自然保护区位于重庆市南川区境内，处于四川盆地东南缘与云贵高原的过渡地带，区内群峰起伏、沟壑纵横，野生动植物资源极为丰富，生物多样性十分突出，是中国珍贵的生物物种基因库和天然植物园。重庆金佛山国家级自然保护区主要保护对象是银杉、黑叶猴及其森林生态系统，是森林生态系统类型的自然保护区。",
            "四面山（SiMian Mountain），位于重庆市江津区四面山镇，距重庆中心城区、四川泸州、贵州遵义均100余千米，处于渝川黔旅游金三角核心地带，集山、水、林、瀑、石于一身，融幽、险、雄、奇、秀为一体，自然景观独特、生态环境优美、旅游资源丰富，是休闲度假的旅游目的地。四面山景区幅员面积213.37平方千米，海拔高度1000~1500米，主要有望乡台、土地岩、龙潭湖、洪海、珍珠湖等核心景区，共128个景点，有“奇山”“异水”“红石”“厚文”四大特色景观。四面山是国家级风景名胜区，森林覆盖率达到95.4%，有动植物约1700多种，是地球同纬度仅存的亚热带原始常绿阔叶林带，被联合国生态保护专家确定为地球上难得的“天然物种基因库”。",
            "重庆云阳龙缸国家地质公园，国家AAAAA级旅游景区、国家地质公园、2016国庆“旅游服务最佳景区”，被旅行者称为长江三峡最后的“香格里拉”，被户外爱好者誉为重庆版的小华山。云阳龙缸风景区位于重庆市云阳县境内东南隅，紧邻湖北利川市，集天坑、峡谷、溶洞、高山草场、森林、土家风情于一体，主要景点有龙缸天坑、云端廊桥、龙洞风光、龙窟峡、岐山草场、蔈草古长城、岐阳关古道遗址、盖下坝湖泊等。其中龙缸天坑深335米，居全国第三、世界第五，龙缸天坑的坑壁由峭壁拱成，倾斜幅度近90度，这种直上直下的形态在世界上极为罕见，享有“天下第一缸”的美誉。云阳龙缸风景区建在海拔1010米高悬崖上的“云端廊桥”，以“天空之花”的花瓣作为造型，廊桥上可以720°欣赏周边美景。其悬挑长度26.68米，廊桥距离地面高度718米，比美国科罗拉多大峡谷玻璃廊桥悬挑长度还长5.34米，是世界最长悬挑玻璃廊桥。云阳龙缸景区内地貌奇特，溶洞密布，奇峰怪石、石笋摩天、雄险俊秀、千姿百态、多彩多姿，是自然科学的博物馆、地质景观的大观园。龙缸四季，景色各异，春之花草，夏之飞瀑，秋之红叶，冬之白雪，无不令人为之叫绝。 2017年2月25日，新晋为国家5A级旅游景区。",
            "阿依河原名长溪河，发源于贵州省务川县分水乡，向东北蜿蜒而入重庆市彭水县境，经长旗坝、舟子沱、三江口，由万足乡长溪滩处注入乌江。阿依河地处重庆市彭水苗族土家族自治县，苗家人把善良、美丽、聪慧的女子称为“娇阿依”，阿依河因此得名。阿依河景区融山、水、林、泉、峡于一体，集雄、奇、险、秀、幽于一身。景区分为3个观光游览区（峡谷观光区、步游观光区、竹筏观光区）、游览项目有8大项目：峡谷听音、竹筏放歌、碧潭戏水、浪遏飞舟、情定苗寨、青龙天梯、青龙洞和青龙谷等。徒步穿行，可观奇花异草，古藤老树；荡舟江上，可享激流险滩，惊涛碧浪；夜宿山寨，可品苗家美味，体验民族风情，是休闲观光、民俗体验、户外攀岩及水上运动的好去处。"
};
    //长沙市
    private String[] changs = {"岳麓山","橘子洲","长沙世界之窗","湖南省博物馆","天心阁","太平街","火宫殿","谢子龙影像艺术馆","华谊兄弟电影小镇"};
    private int[] csicons = {R.drawable.baiyunshan,R.drawable.langu,R.drawable.tianchi,R.drawable.jinshan,R.drawable.helin,R.drawable.liancun,R.drawable.shezu,R.drawable.mindonggeming,R.drawable.mindonggeming,R.drawable.yanghongse,R.drawable.fuanbowuguan};
    private String[] csbuy = {"¥60","¥20","¥80","免费","免费","免费","免费","免费","¥180"};
    private String[] csintroduction ={"岳麓山，因南北朝刘宋时《南岳记》“南岳周围八百里，回雁为首，岳麓为足”而得名。岳麓山位于湖南省长沙市岳麓区湘江西岸；橘子洲位于湘江中，由南至北，纵贯江心，西瞻岳麓，东临古城。岳麓山景区内现有植物174科559属977种，以典型的亚热带常绿阔叶林和亚热带暖性针叶林为主，部分地区还保存着大片的原生性常绿阔叶次生林，并有大量珍贵的濒危树种和年代久远的古树名木。岳麓山是融中国文化精华的儒、释、道为一体的文化名山。它包容了历史上鸿儒巨子、高僧老道、骚人墨客共同开拓的岳麓山文化内涵。岳麓山风景名胜区面积35.20平方千米，包括麓山景区、天马山景区、橘子洲景区、桃花岭景区、石佳岭景区、寨子岭景区、后湖景区、咸嘉湖景区等八大景区。规划在风景名胜区范围以外的外围保护区面积22.68平方千米。已经建成的有麓山景区和橘子洲景区，天马山景区景观建设基本完成。麓山景区面积5.28平方千米，位于湘江西岸，是较为难得的城市山岳型风景名胜区。橘子洲景区整体开发陆地面积达91.64公顷，总投资约14亿元，游客年容量合理估算为209.46万人次/年。此外岳麓山还有四大书院之一的岳麓书院，新民学会等景点。",
            "橘子洲，位于湖南省长沙市岳麓区的湘江中心，原面积约17公顷，景区整体开发陆地面积达91.64公顷，是湘江下游众多冲积沙洲中面积最大的沙洲，被誉为“中国第一洲”，由南至北，横贯江心，西望岳麓山，东临长沙城，四面环水，绵延十多里，狭处横约40米，宽处横约140米，形状是一个长岛。橘子洲有毛泽东青年艺术雕塑、问天台等景点，史载橘子洲生成于晋惠帝永兴二年（305年），为激流回旋冲积、沙石堆积而成。景区内生长着数千种花草藤蔓植物，其中名贵植物就有143种。还有鸥、狐、獾等许多珍稀动物。",
            "长沙世界之窗位于湖南省长沙市东北郊区浏阳河畔，是由湖南电广传媒股份有限公司、深圳华侨城控股股份有限公司和香港中旅集团共同投资兴建的文化主题公园。长沙世界之窗始建于1995年，坐落于金鹰影视文化城内，占地40万平方米。1997年10月1日试营业，是一个融先锋时尚活动、大型器械游乐、世界各国建筑奇观、五洲风情歌舞表演、影视拍摄基地于一体的综合性大型主题公园。",
            "湖南博物院（Hunan Museum），位于湖南省长沙市开福区东风路50号，是中国首批国家一级博物馆、中央地方共建的八个国家级重点博物馆之一、湖南省最大的综合性历史艺术类博物馆。湖南省博物馆始建于清光绪二十三年（1897年），今馆址为其新馆，启用于2017年11月29日，2022年7月30日由湖南省博物馆更名为湖南博物院 ，总占地面积4.9万平方米，总建筑面积为9.1万平方米 。据2020年4月博物馆官网显示，湖南博物院有展厅面积为2.7万平方米，有馆藏文物18万余件，尤以长沙马王堆汉墓出土文物、商周青铜器、楚文物、历代陶瓷、书画和近现代文物等最具特色；湖南博物院共设长沙马王堆汉墓陈列和湖南人——三湘历史文化陈列两个基本展览，定期举行特别展览和交流展览 。2008年5月18日，湖南博物院被国家文物局列为首批“国家一级博物馆”；2018年10月11日，湖南省博物馆入选教育部首批“全国中小学生研学实践教育基地”",
            "天心阁位于湖南省长沙市中心地区东南角上，天心区天心路17号。天心阁始建于明末，清乾隆年间重修天心阁。抗战期间因文夕大火烧毁，1983年，重建天心阁。天心阁有楼阁三层，建筑面积846平方米，碧瓦飞檐，朱梁画栋。天心阁景区因天心阁而命名，以天心阁与长沙古城墙为主要景点，与纪念抗日阵亡将士的崇烈亭、崇烈门、《太平军魂》浮雕、历史名人石刻画廊等组成景区核心景观。自古享有“潇湘古阁、秦汉名城”的美誉，天心阁不但是古城长沙的象征，而且见证了长沙的历史发展与变迁。 [14]天心阁现为国家AAAA级旅游景区，中国历史文化名楼。",
            "太平街是长沙古城保留原有街巷格局最完整的一条街，坐落于长沙城区中部，街区以太平街为主线，北至五一大道，南到解放路，西接卫国街，东到三兴街、三泰街；其中重点地段为沿太平街、西牌楼、马家巷、孚嘉巷、金线街、太傅里两侧的历史街区，用地面积5.33公顷。鱼骨状街区200年未变，全长375米，宽不过7米，占地面积约12.57公顷；交通十分便利，是“古老长沙”一缩影；自战国时期长沙有城池开始，是古城的核心地带，历经2000多年没有改变；街区内，小青瓦、坡屋顶、白瓦脊、封火墙、木门窗，是这一带民居和店铺的共同特色；老式公馆则保留了较为原始的石库门、青砖墙、天井四合院、回楼护栏等传统格局。整治后的太平街历史文化街区不仅保留了贾谊故居、长怀井、明吉藩王府西牌楼旧址、辛亥革命共进会旧址、四正社旧址等文物古迹和近代历史遗迹，也给乾益升粮栈、利生盐号、洞庭春茶馆、宜春园茶楼等历史悠久的老字号注入生机。",
            "火宫殿是湖南长沙集传统民俗文化、火庙文化、饮食文化于一体的具有代表性的大众场所，特别是火宫殿的风味小吃享誉三湘。作为湖南省长沙市的著名特色景点，位于长沙市坡子街。火宫殿同时也是一家驰名中外的“中华老字号”企业，在这里可以吃到各种长沙和湖南小吃，比如长沙臭豆腐、正宗红烧肉、糍粑等等。火宫殿作为美食城在长沙有5处，1家总店，4家分店。",
            "谢子龙影像艺术馆位于长沙市洋湖湿地公园之中，2017年9月16日正式对外开放，旨在“收藏中国影像历史”、“弘扬影像艺术文化”，为公众提供影像艺术服务。",
            "华谊兄弟电影小镇位于长沙市岳麓岳麓其他，由华谊兄弟（长沙）电影文化城有限公司建成，总建筑面积230000，总占地面积666667，共计房屋177户，小区物业公司为万科物业管理公司。"};
    //西安市
    private String[] xian = {"秦始皇兵马俑博物馆","大雁塔·大唐芙蓉园景区","华清宫景区","西安城墙-碑林历史文化景区","大明宫国家遗址公园","汉阳陵博物馆","翠华山国家地质公园","浐灞国家湿地公园","曲江海洋极地公园","西安世博园"};
    private int[] xaicons = {R.drawable.zherong,R.drawable.jiulongjing,R.drawable.dongshishan,R.drawable.xiandujingqu,R.drawable.zherongjiulong,R.drawable.dongyuangujianzhu,R.drawable.fengqiwushidazhai,R.drawable.baizhangyanbaxiandong,R.drawable.pantao,R.drawable.qinglanfengjingqu};
    private String[] xabuy = {"¥60","¥45","¥120","免费","免费","¥60","¥40","免费","¥80","¥70"};
    private String[] xaintroduction ={"秦始皇兵马俑博物馆位于陕西省西安市临潼区秦陵镇，成立于1975年11月，原为秦始皇兵马俑筹建处，于1979年10月1日正式开馆，建于临潼县东7.5公里的骊山北麓的秦始皇帝陵兵马俑坑遗址上，西距西安37.5公里；和丽山园合称秦始皇帝陵博物院。截至2020年1月，秦始皇兵马俑博物馆已接待海内外观众达8000多万人次。秦兵马俑地下大军先后接待观众近5000万人次，其中共接待外国国家元首、政府首脑187批，副总统、副总理和议长506批、部长级客人1852批。截至2020年1月，已先后建成并开放了秦俑一、三、二号坑和文物陈列厅。目前秦俑博物馆面积已扩大到46.1公顷，拥有藏品5万余（套）件。一号兵马俑坑内约埋藏陶俑、陶马6000件，同时还有大量的青铜兵器；二号兵马俑坑内埋藏陶俑、陶马1300余件，二号俑坑较一号俑坑的内容更丰富，兵种更齐全；三号俑坑的规模较小，坑内埋藏陶俑、陶马72件；陈列厅内有一、二号铜车马。",
            "大雁塔位于唐长安城晋昌坊（今陕西省西安市南）的大慈恩寺内，又名“慈恩寺塔”。唐永徽三年（652年），玄奘为保存由天竺经丝绸之路带回长安的经卷佛像主持修建了大雁塔，最初五层，后加盖至九层，再后层数和高度又有数次变更，最后固定为所看到的七层塔身，通高64.517米，底层边长25.5米。大雁塔作为现存最早、规模最大的唐代四方楼阁式砖塔，是佛塔这种古印度佛寺的建筑形式随佛教传入中原地区，并融入华夏文化的典型物证，是凝聚了中国古代劳动人民智慧结晶的标志性建筑。",
            "唐华清宫，是唐代封建帝王游幸的别宫。后也称“华清池”，位于陕西省西安市临潼区。华清宫背山面渭，倚骊峰山势而筑，规模宏大，建筑壮丽，楼台馆殿，遍布骊山上下。初名“汤泉宫”，后改名温泉宫。唐玄宗更华清宫，因在骊山，又叫骊山宫，亦称骊宫、绣岭宫。华清宫始建于唐初，鼎盛于唐玄宗执政以后。唐玄宗悉心经营建起如此宏大的骊宫，他几乎每年十月都要到此游幸。岁尽始还长安。安史之乱后，政局突变，华清宫的游幸迅速衰落，唐朝以后各代皇帝已很少出游华清宫。后历代皇家有维修，到解放前已是汤池寥落，宫殿萧疏。华清池，解放后人民政府自1959年起进行了大规模的扩建。",
            "西安城墙，是中国现存规模最大、保存最完整的古代城垣 [1-7]，是第一批全国重点文物保护单位、国家AAAAA级旅游景区 [8-10]。广义的西安城墙包括西安唐城墙和西安明城墙，但一般特指狭义上的西安明城墙。西安明城墙位于陕西省西安市中心区，墙高12米，顶宽12—14米，底宽15—18米，轮廓呈封闭的长方形，周长13.74千米。城墙内人们习惯称为古城区，面积11.32平方千米，著名的西安钟鼓楼就位于古城区中心。西安城墙主城门有四座：长乐门（东门），永宁门（南门），安定门（西门），安远门（北门），这四座城门也是古城墙的原有城门。从民国开始为方便出入古城区，先后新辟了多座城门，至今西安城墙已有城门18座。",
            "大明宫国家遗址公园，位于陕西省西安市新城区自强东路585号。大明宫始建于唐贞观八年（634年），始称永安宫；贞观九年（635年）正月，改名大明宫，为唐王朝200余年间的统治中心。唐天祐元年（904年），大明宫废毁，沦为废墟。2010年10月1日，大明宫国家遗址公园建成开放。大明宫是唐长安城“三大内”（太极宫、大明宫、兴庆宫）中最为辉煌壮丽的建筑群，地处长安城北部的龙首原上，主要有含元殿、麟德殿、三清殿、清思殿、宣政殿和紫宸殿等宫殿。大明宫国家遗址公园规划范围南至自强路，北至重玄路及玄武路，东至太华南路，西至建强路，总占地约3.84平方千米，公园基本还原了唐代大明宫的历史原貌，并在文物保护基础上，体现了旅游的元素。 [10]大明宫国家遗址公园延续唐代大明宫的历史格局，由南向北沿丹凤门—含元殿—宣政殿—紫宸殿—玄武门—重玄门，为中轴线，分为殿前区、宫殿区、宫苑区三大区域。大明宫国家遗址公园是西安市最大的城市中央公园，形成六大亮点和十个典型游览景点。大明宫国家遗址公园是西安城市建设、大遗址保护和改善民生的重点工程，西安的“城市中央公园”。",
            "汉景帝阳陵博物院（Hanyangling Museum），位于陕西省咸阳市渭城区泾河工业园机场路东段，1999年9月30日建成开放，总面积20平方公里。1963年4月，汉景帝阳陵被公布为陕西省第一批重点文物保护单位。2001年6月25日，汉景帝阳陵被国务院公布为第五批全国重点文物保护单位。2008年5月，汉景帝阳陵博物院荣获“国家一级博物馆”称号。 2010年10月9日，汉景帝阳陵博物院被国家文物局公布为第一批国家考古遗址公园。",
            "翠华山，位于陕西省西安市长安区太乙宫镇，系终南山的一个支峰，位于秦岭北麓，距西安市区20千米，主峰海拔2604米，总面积32平方千米。因汉武帝曾在这里祭祀过太乙神，故又名太乙山。山腰有翠华庙，内供翠华姑娘塑像。民间传说翠华姑娘为争取自由婚姻，逃奔这里，后来成仙而去，此山便得名翠华山。翠华山由天池山崩科普区、甘湫池森林健身区、翳芳湲生态休闲区三部分组成。翠华山以山崩地貌而著称，集山、石、洞、水、林等为一体。主要景点有天池、甘湫池、地质博物馆、汉武帝拜谒太乙神道场等。",
            "陕西西安浐灞国家湿地公园是国家AAAA级旅游景区，位于陕西省西安市境内，地处西安东北部灞河入渭口三角洲区域，属内陆河流湿地，具有中国西北地区河口湿地的典型特征，是浐灞生态区乃至西安市湿地系统的重要组成部分。湿地公园总规划面积8715亩，分为“野趣、精致、时尚、河道”四大展示板块。",
            "西安曲江海洋世界项目占地90亩，主要由海洋馆、海韵广场、海洋商务会所三部分组成。核心工程海洋馆主体建筑面积18600平方米；馆内水体总量约为 6000吨，设计养殖的淡水、海水生物达300余种12000余尾（只）；主要由海豚表演馆、海洋科普馆、热带雨林馆、海底隧道、水下大观园五部分及配套的服务设施组成。它填补了西北旅游资源的空白，并在国内同等项目中创造了五项之最，成为一道靓丽的旅游风景线。",
            "西安世博园，原名西安世园公园，位于陕西省西安市灞桥区广运潭，始建于2008年11月10日，于2012年4月28日正式对外开放。西安世博园为2011西安世界园艺博览会会址，总占地面积418公顷。园区内主要建筑有长安塔，珍宝馆，自然馆，萌宠馆，集文化艺术珍品展示，珍稀动植物观赏为一体；主题园艺景点包括长安花谷，世界庭院，国内展园，省内展园等.2012年，西安世博园被评为国家AAAA级旅游景区。2013年，西安世博园被国家旅游局评为2013年国家生态旅游示范区。"
    };
    //香港
    private String[] xiangg = {"维多利亚港","太平山顶","石澳半岛","庙街","文武庙","金紫荆广场","尖沙咀","海洋公园","天坛大佛"};
    private int[] xgicons = {R.drawable.tailaoshan,R.drawable.niulang,R.drawable.cuijiao,R.drawable.jiulixipu,R.drawable.xiaobailu,R.drawable.dayushan,R.drawable.yushandao,R.drawable.chixicun,R.drawable.shilancun,R.drawable.chengbao,R.drawable.diantoumazu,R.drawable.fudingxiangshansi,R.drawable.tianmenlingyouqu,R.drawable.gubao,R.drawable.lingfengshi,R.drawable.qiyunshi,R.drawable.xiyanglaorenqiao,R.drawable.fuyao,R.drawable.ziguo};
    private String[] xgbuy = {"免费","¥20","免费","免费","免费","免费","免费","¥120","¥80"};
    private String[] xgintroduction ={"维多利亚港（英语：Victoria Harbour）简称维港，是位于中华人民共和国香港特别行政区的香港岛和九龙半岛之间的海港。世界三大天然良港之一。由于港阔水深，中国香港亦因而有“东方之珠”、及“世界三大夜景”之美誉。维多利亚港的名字，来自英国的维多利亚女王。一年四季皆可自由进出。早年已被英国人看中有成为东亚地区优良港口的潜力，后来从清政府手上强占香港，发展其远东的海上贸易事业。维多利亚港一直影响香港的历史和文化，主导中国香港的经济和旅游业发展，是中国香港成为国际化大都市的关键之一。",
            "太平山顶，中国香港首屈一指的旅游名胜，山顶凌霄阁由著名英国建筑师特里·法雷尔(Terry Farrell)设计，外形呈十分独特的碗形，是集观光、娱乐、购物于一身的香港必游地点。其外貌广被全球数以百万计的明信片及照片取用。莅临香港最高360度观景台凌霄阁摩天台428，尽是目不暇给的视觉体验，让您站于海拔428米的无敌观景台之上，在全港最优越的地理位置，饱览醉人都会景致，大有君临天下之感。另外，凌霄阁更设有凌霄展廊，巨型展板展出香港早期的珍贵照片，与市民重返昔日，体会香港百年变化。",
            "石澳半岛（英文：Shek O Peninsula）为中国香港一半岛，位于香港岛南区东部，得名自半岛南端的石澳。石澳半岛和鹤咀半岛以石澳道为界，面向南中国海。石澳郊野公园有大半的部分位于石澳半岛。石澳半岛由多个山峰组成，主要为云枕山、打烂埕顶山和观音山。在云枕山和打烂挰顶山上，连绵的山脊被称作“龙脊”，有一行山径，为绝佳观景地点。",
            "庙街（Temple Street）位于中国香港九龙油麻地，是香港一条富有特色的街道，同时也是香港最负盛名的夜市。很多电影都曾以该条街道取景。庙街以售卖平价货的夜市而闻名，被喻为香港的平民夜总会。",
            "文武庙（Man Mo Temple）位于中国香港上环荷李活道，约于清道光二十七年（1847）至清同治元年（1862）期间落成，是香港受英国殖民统治时期最早兴建的中式庙宇之一。文武庙为庙宇组群，由文武庙、列圣宫和公所三幢建筑物组成，其中文武庙主要为供奉文昌及武帝，列圣宫则用作供奉诸神列圣，公所为区内华人议事及排难解纷的场所，三幢建筑物以两条小巷分隔。文武庙反映了昔日香港华人的社会组织和宗教习俗，具有重要的历史和社会意义。",
            "金紫荆广场（Golden Bauhinia Square）位于香港会展中心的新冀海旁的博览海滨花园内。“永远盛开的紫荆花”雕塑－金紫荆雕像矗立于香港会议展览中心新翼海旁的博览海滨花园内。金紫荆广场位于香港会展中心旁，是为纪念香港回归祖国而设立。金紫荆广场位于香港湾仔香港会议展览中心新翼人工岛上，三面被维港包围，在维港的中心位置，与对岸的尖沙咀对峙，是观景的好地方。",
            "尖沙咀（英文名：Tsim Sha Tsui，亦作尖沙嘴），古称尖沙头，旧名香埗头。昔日东莞一带遍植香木，香木树根所榨得的汁能作香料，名为莞香。由于香港水路交通便利，故香料会先集中贩运至尖沙咀，再用船运至港岛黄竹坑的石排湾，然后从水路运往内地。在明代已发展成一聚落。尖沙咀是九龙油尖旺区的一部份，位于九龙半岛的南端，北以柯士甸道至康庄道为界，与香港岛的中环及湾仔隔着维多利亚港相望。从地理学的角度看，尖沙咀是九龙半岛南端的一个海角，毗邻红磡湾。在移山填海之前，由于该处附近的海水被官涌山所阻，其南端形成一个长及尖的沙滩，地形上十分显著。尖沙咀的原海岸线约在梳士巴利道、漆咸道一带。该处原有两个平行的海角：九龙角和黑头角。两个海角之间还有一个小海湾。经过多次填海工程后，今天的尖沙咀已增加不少土地面积，却依然是一个高度发展区域，一直是香港的心脏的地带。",
            "香港海洋公园（Ocean Park Hongkong）位于中国香港港岛南区黄竹坑，占地超过91.5公顷，在1977年1月10日开幕，是一座集海陆动物、机动游戏和大型表演于一身的世界级主题公园，也是全球最受欢迎、入场人次最高的主题公园。公园依山而建，分为“高峰乐园”及“海滨乐园”两大主要景区，以登山缆车和海洋列车连接。2012年，香港海洋公园获国际游乐园及景点协会博览会(IAAPA)颁发顶尖荣誉大奖“2012 Applause Award”（全球最佳主题公园），成为亚洲首家获得此项殊荣的主题公园。",
            "天坛大佛是全球最高的户外青铜坐佛，巍峨趺坐于海拔482米的香港大屿山木鱼峰上。这尊由宝莲禅寺筹建，历时12年落成的庄严宏伟大佛，寓意香港稳定繁荣，国泰民安，世界和平。天坛大佛由200块青铜铸件组成，高26公尺，重达220公吨，是全球最大的户外青铜坐佛。大佛面相参照龙门石窟的卢舍那佛，衣服的纹理和头饰则参照敦煌石窟第36窟的释迦牟尼佛像。大佛坐在莲花上，取其出污泥而不染之意。天坛大佛是香港重要的地标，吸引众多中外信徒和游客前来朝拜参观。天坛大佛除了是一项杰出的工程外，更是近代佛教造像艺术的优异成就，亦是人类珍贵的文化遗产。"};
    //澳门
    private String[] aom = {"澳门大三巴牌坊","澳门妈阁庙","澳门主教山小堂","澳门石排湾郊野公园","澳门卢廉若公园","澳门八景","澳门博物馆","澳门观光塔"};
    private int[] amicons = {R.drawable.yangjiaxi,R.drawable.luohanxi,R.drawable.chianqiao,R.drawable.shengmuxinggong,R.drawable.jiniantang,R.drawable.xiaputantu,R.drawable.beiqitangtu,R.drawable.xiapuxiancheng,R.drawable.xiaohaohait,R.drawable.qiduxi};
    private String[] ambuy = {"¥100","¥80","¥40","免费","¥60","免费","¥120","免费"};
    private String[] amintroduction ={"大三巴牌坊（葡萄牙语：Ruínas da Antiga Catedral de São Paulo），别名圣保禄大教堂遗址，位于中国澳门特别行政区花王堂区炮台山下。大三巴牌坊前身为圣保禄教堂，始建于明万历三十年（1602年），于清道光十五年（1835年）1月26日被大火焚毁，仅余下大三巴牌坊。大三巴牌坊的雕刻和镶嵌较为精细，融合了东西方建筑艺术的精华，是一个中西文化交融的艺术品。大三巴牌坊前临68级石阶，前壁遗址为23米宽，25.5米高，为巴洛克风格，以花岗岩建成，上下共分五层。",
            "妈阁庙（葡萄牙语：Templo A-Má），古称海觉寺，又名妈祖阁，俗称天后宫，位于中国澳门特别行政区妈阁庙前地，是世界文化遗产——澳门历史城区的重要组成部分。妈阁庙位于半岛西端内港入口处，背山面海，主要由大门、牌坊、正殿、弘仁殿、观音阁和正觉禅林组成，有石狮护门，飞檐凌空，饶有中国民族建筑特色。",
            "主教山小堂，又称海崖圣母小堂建于澳门半岛最高点之一的西望洋山上，是一座向航海者的保护神祈祷的小教堂。建造这座教堂的原因是由于当时执行去日本航线的葡萄牙航海者在海上与荷兰海盗船相遇，却未受伤害，深信是神灵保佑，许下建堂诺言以谢神思。",
            "石排湾郊野公园位于中华人民共和国澳门特别行政区路环岛西面，北接石矿场，西临石排湾马路，南近高顶马路，东至陆军马路，面积约20公顷，因具有教育、生态学、风景及科学的价值，早在1981年已立法成为保护区，开澳门自然教育的先河，1984年成为中国澳门第一个郊野公园。",
            "卢廉若公园位于澳门半岛的中部，东望洋山的北麓，罗利老马路与荷兰园马路的交界处，总面积1.78公顷。园内景色如诗如画，俨然一幽雅、秀丽、恬静的江南风光，颇具苏州狮子林的格局，给人一种“小中见大”的感觉，是港澳地区唯一具有苏州园林风韵的公园。",
            "“澳门八景”，是指早在1992年，由澳门楹联学会、澳门摄影学会、澳门历史学会、澳门中华诗词学会、中区扶轮社、澳门美术协会、澳门狮子会和澳门教育文化艺术学会八个社会团体，组成了澳门八景评议会。评选以综合考虑“历史性、时代性、可观性和连续性“为原则，一年后，从候选的43个景点中挑选出灯塔松涛、镜海长虹、妈阁紫烟、普济寻幽、三巴圣迹、卢园探胜、龙环葡韵、黑沙踏浪等“澳门八景”。",
            "澳门博物馆位于中华人民共和国澳门特别行政区澳门博物馆前地112号，是一个综合性博物馆，总面积为2800平方米，实际展览面积约为2100平方米。1998年4月19日落成并对外开放，由葡萄牙总理古特雷斯（安东尼奥古特雷斯）主持剪彩仪式。澳门博物馆主要分为三层，第一层展示澳门的早期历史，第二层展示了澳门民间艺术与传统，第三层第一层展示当代澳门的物品；澳门的博物馆利用了立体、光、声、像等高新技术，利用复制品、模型来强化陈列效果，主要收藏了书画、火柴业、外销瓷、民俗礼仪、路环黑沙考古遗址、爆竹业的物品。",
            "澳门旅游塔（葡文：Torre de Macau，英文:Macau Tower)，港澳地区习称为观光塔，是一座位于中华人民共和国澳门特别行政区的高塔。从地面到它的最高点，总高度为338米，1109英尺(56层）。主观光层位于离地面223米，732英尺高的位置。它是全球独立式观光塔第十位的观光塔，是世界高塔联盟的成员之一。2018年12月31日晚，除夕倒数烟花表演也将在澳门旅游塔对开海面上如约而至。活动以时间为主轴，结合澳门美食、人文、建筑和文创等元素，展现澳门中西文化交融的精髓，刻划澳门人的成长印记及回忆，带领观众展开一段“时光澳游”"};


    private String[] qita = {"古田钱来山景区","大棠滑草场","太姥山","牛郎岗海滨景区","柘荣鸳鸯草场","九龙井","霍童古镇","宁德东湖水利风景区","福建支提山国家森林公园"};
    private int[] qitaicons = {R.drawable.gutianxian,R.drawable.datang,R.drawable.tailaoshan,R.drawable.niulang,R.drawable.zherong,R.drawable.jiulongjing,R.drawable.huotongguzheng,R.drawable.shuili,R.drawable.senglin};
    private  String[] qitabuy = {"¥40","免费","¥90","¥30","¥50","¥40","免费","免费","免费"};
    private String[] qitaintroduce={"钱来山风景区位于福建省宁德市古田县大桥镇钱厝村，是福建首个“钱文化”主题旅游风景区。景区总面积666平方公里，以景区出入口为中心，由荷花梯田、樱花谷、820山谷、飞瀑谷、有钱谷和谷里农场6个片区组成，呈环状分布，游步道总长度约3.8公里。钱来山风景区平均海拔820米，夏季从早到晚均温27℃，是福州周边又一避暑胜地。",
            "大棠休闲农场位于屏南县境内，这里平均海拔830米，年均气温13～18℃，冬无严寒，夏无酷暑,荣获“宁德市休闲农业示范点”。距屏南城关15公里，屏古高速浙洋互通口5公里。",
            "太姥山以花岗岩峰林岩洞为特色，融山、海、川和人文景观于一体，拥有山峻、石奇、洞异、溪秀、瀑急等众多自然景观以及古刹、碑刻等丰富的人文景观，在这里，可以登山、观海、探洞，也可以泛溪、寻古、采风……",
            "牛郎岗海滨这里气候冬暖夏凉，素以“碧海金沙好消夏”而吸引各地游客慕名而至。牛郎岗海滨沙滩平坦、明净，环山绿树成荫，周围礁石造型各异，有鸳鸯礁、织女洞、海上一线天等自然景观。",
            "柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "九龙井风景区是生态示范区、省级园林县城福建省柘荣县生态旅游的一朵奇葩，是世界地质公园——白云山石臼群的重要组成部分，也是泛太姥旅游圈、闽东亲水游线路的重要组成部分，她集形态各异的龙井群、冰臼奇观、瀑布、青竹走廊、原生态山谷等景观于一体，由九龙井生态景区、金溪漂流景区（筹建中）、石山洋生态观光区三部分组成。",
            "霍童古镇是久负盛名的千年古镇，也是中国民间文化艺术之乡。霍童原名霍山，西周时霍桐真人在此地修炼，故名。霍童的美不仅在于幽美的生态环境，千年的文化积淀也使得这里散发着更大的古镇魅力。说到霍童，不可不提霍童线狮，作为第一批国家级非物质文化遗产，霍童线狮以其独特的艺术表现形式受到人们的喜爱，以其精彩的表演博得台下的观众的阵阵喝彩，这些的种种都奠定了霍童线狮在中国民俗文化中的地位，有“中华绝活”的美名。",
            "宁德东湖水利风景区是国家级水利风景区，位于福建省宁德市。总面积4.48平方公里，其中水域面积2.91平方公里，包括环东湖南、北岸公园和大门山、乌龟山、金蛇山等\"一湖两岸三山\"景观。风景区湿地面积较广，是水鸟觅食与栖息的理想场所。请看正文了解宁德东湖水利风景区的概况及旅游攻略。宁德东湖水利风景区东起金马海堤-金蛇头码头西接福宁北路，南达塔山路-南湖滨路，北至闽东中路-北湖滨路。",
            "第一高峰\"旗山\"好风光地处山区的虎贝镇，海拔高，境内山峦起伏，从而形成了独特的高山气候与风景。第一旗山，因其形如风中之旗而得名。它位于虎贝镇境内文峰、梅鹤村附近，堂义村后山。上山有多条路线:其一虎贝直接上山，其二虎贝黄家村后山上山，其三虎贝乡彭家村后山上山(路程最近)，也是唯一一条公路路线，通常登山游客都选此条，被称通往宁德高峰\"第一旗\"的必备之路。其四，堂义村后山走最美，经过竹林，森林，小溪，还有野菜，野果采摘,经过兔耳岭景区，这里风景独好，是旅游最佳路线。"
    };
    private String[] qitaname ={"古田县","屏南县","福鼎市","福鼎市","柘荣县","柘荣县","蕉城区","蕉城区","蕉城区"};
    private String[] nametwo = {"三都澳","霍童古镇","宁德东湖水利风景区","福建支提山国家森林公园","宁德洋中水利风景区","宁德蕉城区上金贝中华畲家寨","洪口水库","宁德鹤林宫","宁德市博物馆",
            "古田钱来山景区","翠屏湖","齐云寺","古田溪山书画院","长洋徐氏古民居群","金钟湖山庄","凤林祠","凤林祠坐","蝉林祠","圆瑛故居","古田临水宫",
            "白水洋","鸳鸯溪","大棠滑草场","际头耕读文化大观园","漈头古村","纱帽岩","万安桥","百丈漈瀑布","仙山牧场","鼎潭仙宴谷","国家地质公园","观景台","仙女瀑",
            "鲤鱼溪","九龙漈风景名胜区","陈峭古村","周宁滴水岩","林公忠平王祖殿","周宁般若寺","九龙石窟","高山明珠","浦源郑氏宗祠",
            "犀溪漂流","西浦","灵峰禅寺","南山风景区","下党村","小托水库水利风景区","寿宁县生态茶园","犀溪","中共闽东特委含溪旧址","车岭古道",
            "福安白云山","兰谷温泉度假村","天池草场","金山大峡谷","狮峰寺","廉村","穆云畲族乡","闽东革命纪念馆","福安文化馆","柏柱洋红色旅游景区","福安市博物馆",
            "柘荣鸳鸯草场","九龙井","宁德东狮山","仙都胜境景区","柘荣县九龙井水利风景区","东源古建筑群","凤岐吴氏大宅","百丈岩八仙洞","蟠桃映翠","青岚湖水利风景区",
            "太姥山","牛郎岗海滨景区","翠郊古民居","九鲤溪瀑","小白鹭海滨度假村","大嵛山岛","嵛山岛","赤溪村","石兰村","潋城城堡","点头妈祖宫","福鼎香山寺游览区","天门岭游览区","冷城古堡位","灵峰寺","国兴寺","西阳老人桥","瑶列岛国家级海洋公园","资国寺",
            "杨家溪","罗汉溪景区","赤岸村","霞浦松山天后圣母行宫","空海大师纪念堂","霞蒲滩涂","北岐滩涂","霞浦县城","小皓海滩","七都溪"
    };
    private int[] iconstwo = {R.drawable.sanduao,R.drawable.huotongguzheng,R.drawable.shuili,R.drawable.senglin,R.drawable.yangzhong,R.drawable.shangjinbei,R.drawable.hongshui,R.drawable.helin,R.drawable.bowuguan,
            R.drawable.gutianxian,R.drawable.cuipinghu,R.drawable.qiyunshi,R.drawable.gutianxishan,R.drawable.changyangxunshi,R.drawable.jinzhonghushanzhuan,R.drawable.fenglinshi,R.drawable.fenglinshizuo,R.drawable.changlinshi,R.drawable.yuanyinguju,R.drawable.gutianlinshuigong,
            R.drawable.baishuiyang,R.drawable.yuanyangxi,R.drawable.datang,R.drawable.daguanyuan,R.drawable.jitoucun,R.drawable.shamaoyan,R.drawable.wanganqiao,R.drawable.baishangpubu,R.drawable.xianshanmuchang,R.drawable.dingtanxianyangu,R.drawable.guojiagongyuan,R.drawable.guanjingtai,R.drawable.xiannvpu,
            R.drawable.liyuxi,R.drawable.jiulongfengjingmingshengqu,R.drawable.chenxiaogucun,R.drawable.zhouningdishuiyan,R.drawable.zudian,R.drawable.zhouningbanruosi,R.drawable.jiulongshiku,R.drawable.gaoshanmingzhu,R.drawable.puyuanzongsi,
            R.drawable.xixi,R.drawable.xipu,R.drawable.lingfeng,R.drawable.nanshan,R.drawable.xiadangcun,R.drawable.shuikufengjingqu,R.drawable.shengtaichayuan,R.drawable.xi,R.drawable.mindongjiuzhi,R.drawable.chelinggudao,
            R.drawable.baiyunshan,R.drawable.langu,R.drawable.tianchi,R.drawable.jinshan,R.drawable.lingfeng,R.drawable.liancun,R.drawable.shezu,R.drawable.wenhuaguan,R.drawable.wenhuaguan,R.drawable.yanghongse,R.drawable.fuanbowuguan,
            R.drawable.zherong,R.drawable.jiulongjing,R.drawable.dongshishan,R.drawable.xiandujingqu,R.drawable.zherongjiulong,R.drawable.dongyuangujianzhu,R.drawable.fengqiwushidazhai,R.drawable.baizhangyanbaxiandong,R.drawable.pantao,R.drawable.qinglanfengjingqu,
            R.drawable.tailaoshan,R.drawable.niulang,R.drawable.cuijiao,R.drawable.jiulixipu,R.drawable.xiaobailu,R.drawable.dayushan,R.drawable.yushandao,R.drawable.chixicun,R.drawable.shilancun,R.drawable.chengbao,R.drawable.diantoumazu,R.drawable.fudingxiangshansi,R.drawable.tianmenlingyouqu,R.drawable.gubao,R.drawable.lingfengshi,R.drawable.guoxingshi,R.drawable.xiyanglaorenqiao,R.drawable.fuyao,R.drawable.ziguo,
            R.drawable.yangjiaxi,R.drawable.luohanxi,R.drawable.chianqiao,R.drawable.shengmuxinggong,R.drawable.jiniantang,R.drawable.xiaputantu,R.drawable.beiqitangtu,R.drawable.xiapuxiancheng,R.drawable.xiaohaohait,R.drawable.qiduxi

    };
    private String[] buytwo={"¥32","免费","免费","免费","免费","¥35","免费","免费","免费",
            "¥44","¥80","¥68","免费","免费","免费","免费","免费","免费","免费","免费",
            "¥120","¥73","免费入园","免费","免费","免费","免费","免费","免费","免费","免费","¥70","¥70",
            "¥60","免费","无需门票","免费","免费","免费","无需门票","免费","免费",
            "¥138","免费","免费","免费","免费","免费","免费","免费","免费","免费",
            "¥56","¥98","¥30","¥38","已关，暂不开放","免费","免费","免费","免费开放","免费","免费",
            "¥55","¥45","免费","免费","¥60","免费","免费","免费","免费","免费",
            "¥90","¥35","¥25","¥325","¥37","¥10","¥50","¥30","免费","免费","免费","¥120","¥137","免费","免费开放或景区停业","¥140","免费","免费","免费",
            "¥100","¥178","免费","免费","免费","免费","免费","无需门票","免费","免费"
    };
    private String[] introducestwo = {"三都澳又名三沙湾、宁德港，位于福建省宁德市东南部，为中国1.84万公里黄金海岸线的中点。距宁德市区30公里，为闽东沿海的“出入门户，五邑咽喉”，是世界级天然深水良港。",
            "霍童古镇是久负盛名的千年古镇，也是中国民间文化艺术之乡。霍童原名霍山，西周时霍桐真人在此地修炼，故名。霍童的美不仅在于幽美的生态环境，千年的文化积淀也使得这里散发着更大的古镇魅力。说到霍童，不可不提霍童线狮，作为第一批国家级非物质文化遗产，霍童线狮以其独特的艺术表现形式受到人们的喜爱，以其精彩的表演博得台下的观众的阵阵喝彩，这些的种种都奠定了霍童线狮在中国民俗文化中的地位，有“中华绝活”的美名。",
            "宁德东湖水利风景区是国家级水利风景区，位于福建省宁德市。总面积4.48平方公里，其中水域面积2.91平方公里，包括环东湖南、北岸公园和大门山、乌龟山、金蛇山等\"一湖两岸三山\"景观。风景区湿地面积较广，是水鸟觅食与栖息的理想场所。请看正文了解宁德东湖水利风景区的概况及旅游攻略。宁德东湖水利风景区东起金马海堤-金蛇头码头西接福宁北路，南达塔山路-南湖滨路，北至闽东中路-北湖滨路。",
            "第一高峰\"旗山\"好风光地处山区的虎贝镇，海拔高，境内山峦起伏，从而形成了独特的高山气候与风景。第一旗山，因其形如风中之旗而得名。它位于虎贝镇境内文峰、梅鹤村附近，堂义村后山。上山有多条路线:其一虎贝直接上山，其二虎贝黄家村后山上山，其三虎贝乡彭家村后山上山(路程最近)，也是唯一一条公路路线，通常登山游客都选此条，被称通往宁德高峰\"第一旗\"的必备之路。其四，堂义村后山走最美，经过竹林，森林，小溪，还有野菜，野果采摘,经过兔耳岭景区，这里风景独好，是旅游最佳路线。",
            "福建支提山国家森林公园位于福建宁德市蕉城区，由平陶山景区、支提山景区、白马山景区与金山景区组成，公园规划总面积为2299.93公顷，公园内森林覆盖率达94.35%。2006年12月27日，国家林业局做出行政许可决定准予蕉城区设立支提山国家级森林公园，定名为“福建支提山国家森林公园”。福建支提山国家森林公园主要景点有双仙谷、百丈瀑坑头水库等。2006年，蕉城区向国家林业局申报申请立支提山国家级森林公园，有关专家也多次深入景区现场考察，并给予推荐。",
            "宁德洋中水利风景区于2016年被列为第十六批全国重点文物保护单位。洋中水利风景区以水库、山潭、河流为依托，结合周边自然和人文旅游资源形成的自然河湖型水利风景区。景区核心项目占地面积约为2.6平方公里，东至三溪电站水库，西至林坂村溪尾水库，北至陈洞村龙潭瀑布，南至东南溪。景区内交通条件便捷、自然生态多样、文化底蕴深厚、水力资源丰富。洋中立足水利区位优势，因地制宜、因势利导，着力抒写“梦里水乡\"的锦绣诗篇。",
            "上金贝\"“中华畲家寨\"景区位于福建省宁德市蕉城区金涵畲族乡上金贝村，距宁德市中心6公里，海拔325米，人口303人。自2007年以来，在各级党委政府和相关部门的支持帮助下，经过数年的努力打造，目前已拥有“全国文明村\"、“福建省4星级乡村旅游经营单位\"、“宁德最美十大乡村一\"、\"宁德市社会主义新农村建设示范村\"、“国家AAA级景区\"等荣誉和称号。上金贝\"中华畲家寨\"景区是在上金贝村进行社会主义新农村建设的基础上创建起来的一个景区，具有与众不同的四个特色:其一，畲族风情文化。",
            "洪口水电站位于宁德市洪口乡境内霍童溪干流峡谷河段，是霍童溪干流梯级开发的第六级水电站,。洪口水电站水库正常蓄水位165米，水面面积8.92平方公里，坝址以上主河道长86.3千米，控制流域面积1701平方公里，约占霍童溪流域面积的75.8%。大坝设计为混凝土碾压重力坝，坝高130米，顶长340米。从坝址至水库末端长约17.22千米，总库容4.497亿立方米。",
            "根据宁德市志、支提图志、宁德文史和霍童各姓氏宗谱的记载，并参考霍童文史等资料，进行探索累集摘录之。鹤林宫位于第一洞天，是中国道教南方发祥地——“霍林洞天”，古时为道教四大名宫之一。其位于福建闽东宁德西北部的霍童山大童峰居五岳之首。考霍童得名，源于三千年前西周时有——霍桐真人\"炼修隐居霍童山丛林中，故号°霍林洞天\"”。《洞天志》云:洞天别有天地，后户可透\"金陵大茅山\"。洞口高丈许，广盈十筋。下极平坦，中有一窟，泉味如醴，旁有小径，黯然纡曲，人迹难到。",
            "宁德市博物馆（闽东畲族博物馆）隶属宁德市文化与出版局，是一家以收藏、展示、研究宁德历史文物和闽东畲族文物为宗旨的综合性博物馆。生动展示了宁德的悠久历史和灿烂文化。闽东畲族文物陈列则以丰富的馆藏内容，从宗教祭祀、服装首饰、生产用具、生活用具和工艺品等方面展示了畲族的婚礼、不同地区不同样式的传统服饰以及生产生活习俗等面貌，为观众了解畲族历史和传统文化提供一个直观而又生动的视角。",
            "钱来山风景区位于福建省宁德市古田县大桥镇钱厝村，是福建首个“钱文化”主题旅游风景区。景区总面积666平方公里，以景区出入口为中心，由荷花梯田、樱花谷、820山谷、飞瀑谷、有钱谷和谷里农场6个片区组成，呈环状分布，游步道总长度约3.8公里。钱来山风景区平均海拔820米，夏季从早到晚均温27℃，是福州周边又一避暑胜地。",
            "翠屏湖位于古田县城东郊，翠屏湖距城关3公里，翠屏湖属亚热带气候，翠屏湖中烟波浩淼，空气清新，四季如春，翠屏湖水域面积达37.1平方公里，翠屏湖蓄水量为6.41亿立方米，水质碧澄（达到国家二级标准）。三十六个大小岛屿，隔水相峙，沿翠屏湖有被省人民政府列为省级文物保护单位的海内外公认的顺天圣母陈靖姑祖庙临水宫。" ,
            "齐云寺建于北宁三年(1066年),位于凤埔与平湖交界,离古田新城20公里,地理条件优越.东靠古田翠屏湖,毗连宁德三都澳;北接屏南白水洋,延伸周宁鲤鱼溪,是通过古田火车站对接黄山、武夷山国家红色旅游线路必经之路。这座千年古刹，寺宇巍峨壮观，殿亭布局合理。建有大雄宝殿，地藏殿，韦伏殿，观音堂、积善堂、客堂，还有莲池宝塔，放生池。去年又增设了红色革命据点纪念堂。寺内塑许多金光闪闪的佛像。寺前竖有清乾隆三十八年福州府古田县正堂的“仁谳”石碑；寺门两柱刻制“齐皆积享先三宝，云可霖福四方”的对联；两条浮雕彩龙，巧夺天工。寺外还建有土地殿、迎客亭、半山歇息亭、水尾亭；去年又在寺前竖立一尊高大的石雕送子观音。",
            "五十年前，古田旧县城东北郊外两溪交汇处的沙坂高地处有座建于宋淳化二年（991）的溪山书院，朱熹曾在此讲学，并题匾曰“溪山第一”，曾圮于水，明清两代曾重建、重修，清代每年盛夏这里是诗人雅士吟哦之所，1952年为大水冲毁，1958年建古田溪水库，溪山书院旧址亦随之沉入湖底，人们总是念念不忘。五十年后的今天在原溪山书院不远处的翠屏湖后垅后岛建起溪山书画院，真是令人欢欣鼓舞。",
            "吉巷乡长洋村有一群古民居，博得专家的高度赞赏，专家坦言：长洋古民居群古朴壮观，独具风姿,文化底蕴深厚，保存状态良好，是福建大地上传统古建筑的一朵奇葩。一位著名画家专程来这里写生，看到这古朴瑰丽的景观，大为惊讶，执意要多留几天多画几幅,才意犹未尽的离开，画家感慨万千地赞赏：这是历史留给后人的无价之宝。",
            "山之灵气，水之秀气，举目望去，一片苍翠。郁郁葱葱的山林环抱农舍四周，远远可见袅袅炊烟升起，影影绰绰的房子置身其间，从农舍走出来便是霍童溪，碧绿的溪水一派平静，水中倒影清晰可见。这是笔者11月25日走进福口的“森林人家”所见到的景象。据市林业局工作人员介绍，“森林人家”是省林业厅和省旅游局共同打造的乡村旅游品牌，现在我市共有14个森林人家，自5月19日开业以来共接待了游客3万人次，收入300万元。",
            "凤林祠,也叫李氏宗祠,坐落于古田县杉洋镇西南3公里处。唐天祐二年（905）创建善院,置前后洋田赡之,立祖祠。后唐天成四年（929）改建善院，曰凤林。由入闽四世祖李灞创建。迄今已1100多年。凤林祠规模宏大，是蓝田八景之一，举目远山则层峦叠翠，近观水景则清秀徊旋。祠东有石柱高耸，乃唐时旧物，诉说当时的辉煌。祠门两侧立32副旗杆碣，均为历代科名登第者所立，至今保存完好，蔚为壮观。",
            "凤林祠坐落在古田县杉洋镇西南凤林山麓下，枕西北面东南，为宫殿式建筑，三进，总建筑面积2000多平方米。三向围以风火墙，前为檐牙风雨墙。祠埕立有32副旗杆碣及七根石柱，为历代科试登第碑刻者所献。",
            "蝉林祠位于古田县杉洋镇西北3公里的狮岩山麓。该祠坐北向南，木构建筑，占地1321.80平方米。中轴线上依次为大门，华表门亭、下游廊、前天井、中在厅、后天井、祖祠厅。",
            "圆瑛故居位于平湖镇端上村，始建于明末崇祯年间。端上开祖文昌公，开村第一屋至今400多年，属明代样式二层木结构。主楼长10.5米，宽5.5米，计面积115.5平方米；其侧房为4Ｘ4米二层楼，计面积32平方米；总计面积147.5平方米。圆瑛居住此屋系圆公祖上遗产。只知圆公爷、父辈三代住进此屋。圆公是开村第十一代后裔，圆公生母阙氏。圆公生于1878年５月12日，居住时间：出生至14岁在此屋居住。15-17岁到古田求学中秀才，18岁游涌泉寺并出家，19岁在家养病，20岁重新出家即1897年。从此离开此屋不复返，距今112年。此屋此后交由堂侄吴贞玉看管、居住。贞玉于上世纪70年代搬出，此屋至今休闲已有30余载。",
            "临水宫是一座风格别致的仿唐代宫殿建筑，始建于唐贞元八年（公元792年），后经元明清历代重修扩建，至今已有1200多年的历史，是分布国内外各地临水宫的祖殿，被福建省人民政府列为省级文物保护单位，现已列入国家级文物保护单位。临水宫是祀典道教女神，海内外公认的“顺天圣母”陈靖姑的宫殿。",
            "白水洋，位于福建省宁德市屏南县，是国家AAAAA级旅游景区。白水洋地质公园园区总面积达77.34平方公里，拥有世界唯一的鸳鸯猕猴自然保护区。白水洋景区是福建省八大旅游品牌之一，2003年11月，福建省委书记卢展工深入白水洋考察时，称白水洋为“天下绝景，宇宙之谜”；国家副总理吴仪游玩白水洋后题“奇特景观”。",
            "鸳鸯溪风景区是我国目前独有的鸳鸯鸟保护区。 鸳鸯溪长14公里，附近山深林密，幽静而清净，是鸳鸯栖息的好地方。每年秋季有数百上千只鸳鸯从北方飞来越冬。" ,
            "大棠休闲农场位于屏南县境内，这里平均海拔830米，年均气温13～18℃，冬无严寒，夏无酷暑,荣获“宁德市休闲农业示范点”。距屏南城关15公里，屏古高速浙洋互通口5公里。",
            "际头耕读文化大观园座落于屏南农耕文化博物馆正面设立在相邻的六幢古民居内,带给人的是原汁原味、古香古色的视觉冲击。潦头村历史悠久,至今已有1135年历史,文化底蕴深厚,历代文物众多，—大批古民居各具特色,就地取材,省时、省工又省钱。目前馆内也只有两位自愿者式的解说员且这种解说完全是免费的，博物馆目前还在不断完善和积累之中馆内还设立有农耕农作的体验区,若带上一家人到此走走，都会有不小的收获。",
            "从屏南县城沿屏宁二级公路往东5公里处,座落着一座一千多年历史的古老村庄――祭头村(古称龙漈乡)。漈头村肇基于唐僖宗干符三年（公元876年),全村方圆31平方公里。现有村民812户4000多人口。漈头村有着悠久的历史,深厚的文积淀，璀璨的人文景观，古朴的民居建筑。",
            "五彩洋的中央，有一突出的石笋，从上方看，很象一顶明代的乌纱帽，故称“纱帽岩”。据说当年有一位县官路过此地，见此处山清水秀，感叹宦海沉浮，遂生退隐之心，将纱帽抛在水中化为此。从左侧往上看纱帽岩，其形如一只巨龟，背上驮着一堆宝物，人称“金龟驮宝”，总体看，又似一鼓满的风帆，又称“一帆风顺”，因此，该景点又有一个十分吉利的名字——“金龟驮宝，一帆风顺”。",
            "万安桥为五墩六孔木拱廊屋桥。正中桥墩上有一嵌入桥墩的碑,碑文云:弟子江积舍钱一拾三贯谷三十二石,结石墩一造，为考批二亲承此良因，又为合家男女及自身乞保平安。元佑五年庚午九月谨题。桥东端有10级石阶，桥西端有36级石阶，桥屋内檐下有13幅楹联。桥西北建有重檐桥亭。穿斗式梁架飞檐走梭,顶盖双披青瓦,工艺堪称巧夺天工,气势雄伟,古朴端庄,不愧为中国古木拱廊桥之最,对研究古桥梁建筑具有重大价值。2006年5月25日作为“闽东北廊桥\"之一公布为第六批国家重点文物保护单位。",
            "百丈飞瀑位于海拔300米至638米的高山之巅(百丈漈镇)，纵深约3公里，面积为4.75平方公里。有着一漈雄、二漈奇、三漈幽等特色。瀑布上游汇集200多平方公里的集雨面积，汇成激流突奔百丈深壑，形成了一漈百丈高、二漈百丈深、三漈百丈宽的阶梯型瀑布群。",
            "仙山牧场位于屏南县县城西北部与古田、建瓯两县交界处，离县城60多公里。牧场现有面积3万多亩，可发展至10万亩，被誉为“北方少有，南方仅有”的高山牧场。",
            "鼎潭仙宴谷位于鸳鸯溪中段大白岩下，为鸳鸯溪四大奇观之一，被称为\"全立体景观\"、\"大环幕风光电影\"、\"中国仅有，世界少有\"。",
            "宁德国家地质公园位于宁德市，坐落在太姥山脉和鹫峰山脉的群山之中，由屏南县白水洋、福安市白云山、福鼎市太姥山三个园区组成，园区总面积2660平方千米，核心区面积383平方千米。",
            "观景台是依山而建、因势而上，像一艘巨轮泊岸。观景台，顾名思义就是揽胜白水洋的平台。观景台内部拥有医疗救护点、超市、休闲区和寄存更衣等设施。为游客免去负担，而站在观景台上眺望平平展展的白水洋，凭栏远眺，偌大个浅水广场，尽收眼底。绿涛唤白浪，山风吹水韵，让人心旷神怡。放眼上洋双龙桥横架两岸，构成了一幅青山、流水、古桥浑然一体的唯美画卷。",
            "仙女瀑是个三级瀑布，借想象会发现三重水瀑相叠像一个仙女正端坐于幽谷中沐浴梳妆，清澈的山水从高处落下，拍打在石头上，变成了一片片白色的水花，走近了都有一股凉意。到了远处，水流没有那么湍急了，仙女瀑没有黄山瀑布那波澜壮阔的气势，也没有泰山瀑布那一泻千里的声威。他只是静静地向下流着，不时地在与岩石的撞击中飞溅出几朵小小的水花",
            "位于周宁县城西五公里处的浦源村中。鲤鱼溪源于海拔1448米的紫云山麓，汇数十条山涧清泉奔流而下，峰回水转，至浦源村口水势顿减，五弯六曲穿村缓流而过",
            "九龙漈风景名胜区位于周宁县东南13公里处。这里峰奇石秀、峡谷幽深，龙江溪在危崖断壁之间层层跌落，形成了九级瀑布群。",
            "陈峭村，是大自然的厚赠，有瑰丽的日出、云海、星空，有磅礴的峭石、岩洞、峡谷，还有千年遗存的街巷、廊桥、民俗。然而，这里因为地处偏僻，交通不便，一直“养在深闺人未识”。",
            "滴水岩风景区位于福建省周宁县城西南30公里的洞宫山麓，与古朴典雅的鲤鱼溪、气势磅礴的九龙祭瀑布群齐名，名载《辞海》。古人题匾曰“八闽首景”，它邻近国家级自然保护区鸳鸯溪的核心地带，依霍童溪上游的叉溪、白水洋溪等处与政和、屏南、宁德三地毗连，山峦起伏，溪涧密布，风景秀丽，成为闽东霍童溪流域旅游风光带的一个重要组成部分。",
            "该殿创建于明正德八年(1513年)，清嘉庆十年(1809年)增建太子亭．该宫坐南朝北，面宽17.27米，深24.38米，总建筑面积为421平方米．正殿面阔三间，进深三间，单檐歇山顶穿斗式(金柱抬粱式)，土木结构．殿前太子亭为三檐歇山顶，顶上有“赐封林公忠平王祖殿”石匾额和建造宫碑，皆为明代遗存。左右为钟、鼓楼，建筑形式为穿斗式双檐歇山顶，瓦栋上有泥雕，保存完好。",
            "般若寺坐落于纯池村尾院林桥旁，始建于后唐天成二年（927年），为纯池下坂许姓始祖所筹建。元朝至正元年（1341年）扩建，清朝道光十六年（1836年）重修。1985年12月天王殿拆除重建。上个世纪90年代末，芹山水库工程时迁址失败，后因水库蓄水，弥勒殿被水淹没而拆除，高水位时，大雄宝殿也被泡在水中，后经围堰，般若寺规模逐渐变小。",
            "九龙石窟(又名蝙蝠洞)游览区，是一处长约3公里的溪谷，流水淙淙，瀑布成群，花奇果异，茂林修篁，谷深洞幽，风景绝佳；特别是其纯净无污染的自然环境更为游客所称颂。",
            "周宁境内峰峦翠，玉带缠绵，钟云毓秀，如诗胜画，是一块令人神往的旅游胜地。境内风光秀丽，旅游资源得天独厚。九龙际瀑布群雄伟壮阔，气势磅礴，荣膺省级首批十大名胜风景区之盛誉；鲤鱼溪神鳞戏水，人鱼同乐，令游人流连忘返；滴水岩泻珠溅玉，水滴石穿，史称“八闽首景”；即将建成的库容3.2亿立方米的高山人工湖令人神往；千年古刹灵峰寺、方广寺山深庙古，闻名遐迩......全县平均海拔800米，县城海拔888米，冠全省之首，冬无严寒，夏无酷署，盛夏日均气温仅24摄氏度，享有“天然空调城”的美誉，是旅游、度假、避暑的好去处。",
            "南宋嘉定二年（1208）始建，明洪武十八年（1387）重建，清道光十年（1830）、光绪二年（1876）重修。平面前窄后宽，呈船形，为三进式传统宗祠建筑，由门厅、戏台、次厅、主厅等组成，建筑面积1830.2平方米。大门外两侧有清咸丰九年（1859）设置的旗杆石4对，祠内有泥塑7尊、木雕神祖牌及名人匾额等。",
            "犀溪文化生态旅游是以犀溪流域为纽带，以文化和生态为基础，以廊桥和古建筑为特色，北起犀溪乡外山村及牛当山，南至仙峰村，西至笔架山，东到长岗头山脊一线，并包括甲坑、石竹州一带，面积约17.8平方公里。旅游资源分布呈现大分散小集中的布局，包括西浦文化园林村、犀溪古建筑群、犀溪水域风光带、甲坑红色旅游地、笔架山山地风光带、石竹州峡谷等。犀溪漂流位于犀溪溪头六公里处，是暑假旅游的好去处。",
            "浦景区位于福建省寿宁县东北部闽浙边界的犀溪镇，是犀溪文化生态旅游区的重要组成部分, 景区距离寿宁县城20公里,距离浙江泰顺县城15公里，规划面积3.5平方公里。景区以生态水系和生态植被为基础，以廊桥和古建筑等生态文化为特色，突出状元、民俗、戏曲文化亮点，建设集旅游观光、运动休闲、研学旅行等于一体的综合型旅游区。",
            "灵峰寺又名灵显寺，位于周宁县纯池镇禾溪与桃坑之间的灵显麓，掩映于苍松翠竹之中，环境幽雅，风景秀丽。该寺坐落于纯池镇桃坑村附近，距镇政府所在地约9公里，海拨790多米，是县重点文物保护单位。",
            "南山风景区位于寿宁南阳，由金鸡山、南山顶、赤陵洋、紫云山、龟湖五个风景区组成，拥有明代古刹龙岩寺，明代文学家、寿宁知县冯梦龙塑像，寿宁革命圣地赤陵洋，闽东第一铁索桥----龟岭索桥.",
            "下党村是寿宁县2014年唯一列入第三批中国传统村落的村庄，村落面朝下党修竹溪依山而建，村落呈梯形分布，房子层层叠叠，交错有序。青山巍峨绿水缠绵，山水之间，木拱廊桥横跨其间，形成了“廊桥、流水、人家”的美景。",
            "小托水库，又名韶托水库。位于福建省寿宁县清源乡小托村，始建于1958年。总库容量426万立方米，有效库容277万立方米。小托水库中有“水中大熊猫”之称的中国一级保护动物——“桃花水母”。",
            "寿宁是个“九山半水半分田”的典型山区农业县，茶产业是传统主导产业，现有茶园11.8万亩，福云6号品种就达到10.5万亩。由于福云6号品种在耕作过程中已失去“早发早采早上市”的优势，无法适应市场消费需求，生产效益低下。2007年冬，寿宁县提出打造“闽浙边界生态新茶乡”的思路，致力茶业品种改良和生态茶园建设，优化产业结构，提高生产效益。",
            "犀溪，因在其西浦村的河滩上发现有印有犀牛脚印的溪石而得名，一个有着江浙风情的闽东小镇。犀溪位于福建宁德市寿宁县的犀溪乡，和浙江的泰顺相连，是一个山清水秀的地方，民风淳朴，风景怡人。作为集浙南乡村灵秀于一身的犀溪，绝对能让远行的你得到所要寻找的美好和宁静。犀溪名字的由来，据说就是因为在其西浦村的河滩上发现有印有犀牛脚印的溪石，现在传说中的犀牛脚印早已无处寻觅，而让远方来客陶醉的景色，就在西浦村上流的河滩处。犀溪文化生态旅游是以犀溪流域为纽带，以文化和生态为基础，以廊桥和古建筑为特色，北起犀溪乡外山村及牛当山，南至仙峰村，西至笔架山，东到长岗头山脊一线，并包括甲坑、石竹州一带，面积约17.8平方公里。旅游资源分布呈现大分散小集中的布局，包括西浦文化园林村、犀溪古建筑群、犀溪水域风光带、甲坑红色旅游地、笔架山山地风光带、石竹州峡谷等。内容来自环视旅游网。犀溪漂流，顺流而下，热情四溢，在5.3公里的河道上漂流总落差达到了180米，时而激流，时面缓潭，可谓有惊无险，乐趣天成。",
            "包括含溪闽东特委旧址、瓦窑坪红军标语、瓦窑坪红军洞等。中央红军长征后，闽东苏区与党中央失去了联系，局势十分严峻。1935年5月，中共闽东临时特委委员叶飞在含溪召开紧急会议，重新建立中共闽东特委，下辖霞鼎等4个中心县委和14个县委，领导闽东地区坚持了三年游击斗争。",
            "车岭古道位于寿宁县斜滩镇斜滩村、清源乡阳尾村，年代为明至清。明中叶形成，由明迄清历代维修，是寿宁通往福宁府的重要古官道。古道自斜滩蜿蜒而上，相对高差658米，全长约10公里。山路用石块随地形铺就，宽1到2米，沿途分布多处路亭、摩崖石刻、关隘，主要有车岭关、一亭、“岭峻云深”石刻、二亭、三亭、四亭遗址、五亭遗址、“去思碑”石刻、平氛关、龙凤亭等。",
            "福建福安白云山，国家风景名胜区、国家4A级旅游景区、世界地质公园、国家地质公园。位于福建省福安市西北部穆云乡、晓阳镇境内，距福安市区约40公里。",
            "兰谷温泉位于溪潭镇兰田村，福安西高速出口300米处，距离福安市区仅6公里，占地面积3.3万平方米。依山而建，总计划投资8000万元，规划有室内外温泉池58个。一期已投入2000万元，以畲医药养颜养生温泉为主题，开发有26个不同主题的养生药泉，二期将重点开发院落式私家温泉，给福安人一个家门口的“治愈系”温泉度假胜地。",
            "天池草场位于福建省福安市松罗乡东山之巅，环境优美，可朝看日出，夕赏晚霞，晴观蓝天白云，雨观云海虹霓，置身连绵起伏的广袤草坪，仿佛临登仙界。景区内设亲子园、拓展园、萌宠园及多种新型游乐设备，服务设施齐全，是亲子、度假、避暑、学研的好去处。",
            "金山大峡谷生态风景区是位于福安市松罗乡东北部，距福安市区仅40余公里，景区游览路线全长3.5公里。金山大峡谷是集运动、体闲、观光、娱乐、养生于一体的乡村生态自然风景区。\n",
            "狮峰寺又称狮峰广化禅寺，位于福安市柏柱洋狮峰山麓，距县城30公里。坐西朝东，傍山而立，建筑雄伟。虽时历千年而庙貌尚存。为闽东佛教千年古刹，属闽东佛教历史最早、规模最大的寺院之一。大殿建筑风格独特，历史价值极高，属国内罕见，1985年10月作为革命旧址被福建省政府核定为第二批省级文物保护单位，2006年5月25日由国务院核定并公布为第六批全国重点文物保护单位。此外，寺中还留有大量丰富的历代文人墨客的名作佳句。1991年，全国政协副主席、中国佛教协会会长赵朴初居士亲题寺名\"“狮峰寺”三字。",
            "廉村位于福建宁德福安市溪潭镇。廉村原名石矶津，是唐朝福建第一个进士薛令之的故乡。廉村被喻为开闽进士第一村，位于福安市溪潭镇穆阳溪中游西岸，旧名富溪津、石矶津，因里人薛令之是福建第一位进士,且为官清廉，被御赐\"廉村\"、“廉水\".“廉岭\"之名。村内主要文物古迹有:明清时期的廉村城墙、陈氏宗祠、陈氏支祠、后湖宫、妈祖庙、陈树安宅、陈住松宅、“聪明泉\"、薛令之故居、薛令之读书处古码头等。传统产业主要是田草织席等。溪潭廉村还有宋代城堡建筑群、宋将陈最墓，赛岐苏阳明将刘中藻墓，康厝东山雪洞等。",
            "穆云畲族乡地处福安市西部，下辖33个村民委员会，其中纯畲族村14个，回族村1个，畲汉杂居村7个。地理交通便利，区位f势明显，省道下浦线、县道福穆线和正在建设中的宁武高速公路贯穿全境，白云山互通口设于桂林村，是福安市\"一市三区六团\"发展布局之生态旅游经济区的重要组成部分。茶叶、穆阳水蜜桃、刺葡萄是农业三大主导产业。全乡茶园面积达10000多前刺葡萄面积6000多亩。境内自然风光瑰丽神奇，独树一帜。国家AA级景区、全省农业旅游示范点-溪塔葡萄沟风景区集农业观光、畲族风情于一体，名闻遐迩。",
            "闽东革命纪念馆馆藏品有从马列主义传播到轰轰烈烈大革命，从工农武装割据、创建闽东苏区到坚持艰苦卓绝的三年游击战争，从奔赴民族解放战争的战场到解放福建期间曾经在这块土地上战斗过的陶铸、叶飞、粟裕、曾志、范式人、杨采衡、陈挺、左丰美等老一辈革命家的照片、家书、传单、书、刊、佩刀、印匣、印、衣服、台灯、砚台、墨盒、矛、戈等革命文物。",
            "福安文化馆现有馆舍三层，总面积达3432平方米。全馆设有文学、音乐、舞蹈、戏曲、美术、摄影等课程，有大、中型排练厅、表演厅、展览厅等。现有工作人员14人，其中副研究员1人、中级馆员6人、助理馆员3人、管理员4人。2008年举办大、中型歌曲、戏剧、小品等文艺晚会30多场，举办大型文化艺术展览10多场，组织文化理论研讨活动和对外交流活动15场次。近年来创办了6个文艺艺术创作基地和4个未成年人文化活动基地，创建了2个美术、舞蹈培训中心。",
            "柏柱洋红色旅游景区是福安溪柄镇旅游发展的核心所在，其独具特色的红色旅游资源是当地旅游的“金名片\"”。该景区位于溪柄镇东南方，距市区30公里，被誉为“闽东延安”。邓子恢、叶飞、陶铸、曾志、马立峰、阮英平、詹如柏等革命前辈在此创建中共闽东特委、闽东苏维埃政府。该景区以红色旅游为主题，集\"红、绿、俗、古、廉\"旅游资源于一体，于2011年被列入国家\"十二五'红色旅游规划名单，中共闽东特委旧址被纳入全省7个红色旅游经典景区。",
            "福安市博物馆位于福安市公园路58号，成立于2004年6月8日,其前身为成立于建国初期的福安市文化馆文物室，人员编制2名,在福安市文体局办公楼内办公。福安市博物馆职责以发现、珍集、收藏、保护、研究与展示宣传历史文化遗产，传播有益于社会进步的思想、道德、科学技术和文化知识，繁荣中华文化事业，推动社会主义精神文明建设，发挥社会教育功能，丰富人民的精神文化生活，提高公众素质。",
            "柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "九龙井风景区是生态示范区、省级园林县城福建省柘荣县生态旅游的一朵奇葩，是世界地质公园——白云山石臼群的重要组成部分，也是泛太姥旅游圈、闽东亲水游线路的重要组成部分，她集形态各异的龙井群、冰臼奇观、瀑布、青竹走廊、原生态山谷等景观于一体，由九龙井生态景区、金溪漂流景区（筹建中）、石山洋生态观光区三部分组成。",
            "东狮山耸立在柘荣县城东2公里处，面积25平方公里。东狮山是太姥山脉的主峰，海拔1480米，气势雄伟。东狮山属花岗岩地貌，岩体、岩洞千姿百态，有皤桃岩、马头岩、象鼻岩。千笋石、万书岩。擎天柱。玉屏洞、灵峰洞、“仙人锯板”等景点，山下龙并瀑布落差100余米，宽6米，蔚为壮观。",
            "仙都胜境景区位于风景区南部，面积约1.4平方公里，以良好的田园和村居民俗为景观特色。主要有梨坪、彭琪垄两个自然村。东狮山也是多彩多姿的生物世界。据初步调查，整个景区栖有长尾雉、黄头雉、真鸟仔、鹁鸪、翠鸟、画眉等四十多种飞禽。“百啭千声随意啼，山花红紫树高低。”徜徉于山水之间，畅游于林泉之下，倾听鸟儿浅唱低吟，无比惬意。东狮山景区内花木品种众多，有桃花、樱花、百合、空谷幽兰，杜鹃更是一绝，每逢春夏之交，满山花开，有红、有白、有黄、有紫，绚丽多彩，白的素洁、红的热烈，顾盼生姿、煞是可爱，“此时逢国色，何处觅天香。”",
            "九龙井水利风景区，她集形态各异的龙井群、冰臼奇观、原生态风貌等景观于一体，两岸青山滴翠、谷中流水潺潺。水以溪水为练，将蝙蝠井、金猪井、观音井、龙门井、阴阳井、瓮子井、长河井、双心井、太阳井等九个龙井串连，动静结合，舒展妩媚和神奇；溪以石为奇，千姿百态的河床、风格迥异的冰臼，一段一景，石得水而活，悬泉飞流的瀑布，深不可测的龙井，不禁让人流连忘返，纷纷赞叹大自然的鬼斧神工。",
            "东源古建筑群位于柘荣县东源乡东源村，年代为清、民国。由吴氏宗祠戏楼、古书堂、培凤亭、古井、粉墙厝和吴成故居组成。",
            "该宅建于清乾隆年间（1736—1795），坐北向南，依山而建。内、外两重围墙，四周花园，通面阔76米，通进深99.1米，总占地面积7531.6平方米（11.3亩），其中建筑占地面积2391.52平方米。第一级台基宽56米，深9.5米。单檐砖木构穿斗式硬山顶门楼，三开间。二门为青砖三合土混合结构，三楼牌坊式顶盖，横额正面行书“凤岐聚秀”，内面行书“仁义为庐”。第二至第五级台基上建四座相隔一定距离的横向楼房，两旁建厢房，正中天井两旁建纵向单扇窗阁式廊庑，将横向楼房隔成三个纵向大院，第三级台基与第四级台基之间横向建空斗防护砖墙，将建筑群分隔成前后两大区域。",
            "百丈朝暾景区位于风景区中部，蟠桃映翠景区的东北面，面积约3.54平方公里，以绝岩峭壁，幽深洞岩为景观特色，主要景点有：罗隐湾、土地岩、清云宫、石门、石将军、风吹洞、石门楼、仙人脚印、观日台、灵岩洞、通真洞、灵峰洞、何仙姑洞、百丈灵岩等。",
            "蟠桃映翠景区位于风景区中西部，面积约1.88平方公里，以溪涧风光为景观特色，洞奇石怪，风景资源多而集中。主要景点有：迎宾亭、仙景岭、普光寺、仙掌泉、蟠桃溪、蟠桃岩、金蟾朝圣、蟠桃洞、马头岩、象鼻岩、玉屏洞、南天门、三曹院遗址、白马宫等。",
            "青岚湖水利风景区位于宁德市柘荣县，依托青岚水库而建，景区面积19.34平方公里，其中水域面积0.71平方公里，属于水库型水利风景区。区域内水体澄清，水质I级;负氧离子含量高，空气质量指数一级；森林覆盖率为86%,水土流失综合治理率高达96%；动植物资源丰富，生态环境优良。",
            "太姥山以花岗岩峰林岩洞为特色，融山、海、川和人文景观于一体，拥有山峻、石奇、洞异、溪秀、瀑急等众多自然景观以及古刹、碑刻等丰富的人文景观，在这里，可以登山、观海、探洞，也可以泛溪、寻古、采风……",
            "牛郎岗海滨这里气候冬暖夏凉，素以“碧海金沙好消夏”而吸引各地游客慕名而至。牛郎岗海滨沙滩平坦、明净，环山绿树成荫，周围礁石造型各异，有鸳鸯礁、织女洞、海上一线天等自然景观。",
            "翠郊古民居是迄今为止在江南地区所发现的单体建筑面积大、保存完好的古民居，堪称江南古民居之杰作。位于福建省福鼎市20公里的白琳镇翠郊村翠郊，离太姥山大概两个小时左右的车程。",
            "九鲤溪瀑景区是太姥山“山、海、川、岛”四大风景名胜区的重要组成部分，主溪流长25公里，汇集13条支流。溪流两岸，青山重回，绿树葱茏，怪石林立，碧如澄沏，水浅处，卵石游鱼，历历可数，舟行其间，如游画中。以田园风光见长，生态环境优美，成为闽东书画院的写生创作中心。荡涤都市尘埃，回归梦幻田园，乘筏漂流溪上，清凉逍遥。",
            "位于福建东北沿海沙埕港以南小白鹭海湾的小白鹭海滨度假村，是由福鼎小白鹭海滨度假村开发有限公司投资开发建设的，以“渔文化民俗游”及“海滨沙滩休闲度假”为主体的海滨休闲度假区，也是国家重点风景名胜区太姥山的“山、海、川、岛”四大旅游休闲基地之一。",
            "大嵛山岛地处福建东南海域部位，因其地理位置优越，一直都是南北船只的必经之地，具有极为重要的意义，主要又11个岛屿组成，现已开采出了大小天湖，白云飞瀑等别具风格的自然景观，是中国最美十大岛屿之一。",
            "嵛山岛 ，素有“海上明珠”之称的，风光旖旎，有天湖泛彩、南国天山、海角晴空等胜景，被列为世界地质公园太姥山风景区四大景观之一，被评为全国十大最美海岛之一。大天湖面积1000多亩，小天湖200多亩，两湖相隔1000多米，各有泉眼，清澈见底。湖四周山坡平缓，是有“南国天山”之誉的万亩草场。",
            "赤溪村位于福鼎市磻溪镇东南部，与霞浦相毗邻，距福鼎市区65公里，离集镇23公里，处名胜风景区太姥山境内，对外交通较为便利。赤溪村是“中国扶贫第一村”，也是少数民族村。其依靠九鲤溪这一生态旅游资源，吸引了来自浙江及闽东各地的许多游客，尤其是假节日、周末更多，显得十分热闹。",
            "石兰村，位于福鼎市硖门畲族乡双狮山后脊，其是一座具有千年历史的城堡式古村落，城堡建筑保存完好，并有被国家林业局列入中国树木奇观的奇特古榕给古堡增添了色彩。其作为历史文化名村，保存着丰富的历史文化遗产和自然景观，吸引了国家、省、市各级各界人士目光，并纷纷撰文、摄影、报道，推动了石兰历史文化名村的宣传和建设。",
            "潋城城堡位于福建福鼎市秦屿镇潋城村。明嘉靖十一年，为抵御倭寇，朝廷委派官员监建，由王、叶、杨、刘等几个大姓分段建城堡。城堡石构，1127米周长，城墙高5～6米，宽4～5米。垣墙高1.5米，厚1.2米；环城设四座炮台，配备四门炮；有东、西、南三个城门，城内有环城路、古街等，水道四通八达；城外有护环城河。",
            "点头妈祖宫，位于福鼎市点头镇海乾路。始建于明代，清康熙年间重建，乾隆二十年重修。头妈祖宫该宫为砖木结构，硬山顶，由亭阁、天井、厢廊、大殿等组成。其宫前大埕开阔，立有两支青石旌表，高约十米，系清光绪年间为褒扬妈祖有功于民而建立之旌表。上部有石斗，刻\"天上圣母\"四字，底座铭文分别为\"光绪二年丙子季秋吉旦\"，右为\"十五都扆山点头社公建\"。",
            "福鼎香山寺游览区位于太姥山西侧，其始建于明朝，原名“妙香庵”。大雄宝殿前有块巨石叫“犀牛望月”，牛颈有一天然岩洞叫“犀牛洞”，洞壁上有一联：“秀句满江国，芳声腾海隅”，乃清户部尚书，同治、光绪皇帝的老师翁同龢所题，旧时佛堂就建在犀牛洞内。香山寺周围的山谷盛产兰花，花开时节，幽香阵阵，故寺名叫“香山寺”。",
            "天门岭游览区位于福建省福鼎市秦屿镇太姥山景区内，从国兴寺起沿着麒麟岗一直盘旋抵至天门寺，全长有2.3公里，于2000年开发修筑的。这条岭外侧林木葱茏，内侧峭壁如削，沿途风景秀丽，岩石景观目不暇接，让人如同行走在充满野趣情调的山荫道上。",
            "冷城古堡位于福鼎市秦屿镇冷城村，建于明嘉靖年间，为抵御倭寇而筑。其外形呈圆形，砖石结构，绕村一圈，周长1127米，高5.6米，厚4.67米。设有东门、西门、南门三道城门，至今保存完好。城内古街、民居、山墙、小巷，古香古色。城中尚有三官堂、猴仙宫等古迹，以及冷城农民暴动等革命遗址。",
            "灵峰寺位于福建省福鼎市境内的太姥山，毗邻东海，壤接江浙，雄镇东南，府控吴越。建于唐太宗贞观三年，宋淳熙十六年遭火焚，明天启七年重建，清光绪十年重建，占地1500平方米，坐北朝南。四周古木障天，树影娑婆，仿古建筑群恢宏壮观、幽深清逸，令人心驰神往。",
            "国兴寺位于福建省福鼎市秦屿镇太姥山，其始建于唐乾符四年（877年），废于宋。据志书记载尚存石柱360根，占地面积为二千五百平方米，三面环水，一面背山，有七根大石柱立于地面，基座为覆盘莲花础，中轴通道旁有唐、宋石雕，内容有花草、神兽等。有宋石斛四个，残缺碑刻多方。1989年1月公布为县首批文物保护单位。民国年间重修。现在大雄宝殿、太姥殿、僧舍等建筑。",
            "西阳老人桥位于福鼎市管阳镇西阳村桥头自然村，始建于明正德年间(1506—1521)，清康熙、同治年间重修， 其是一座木结构的弧形古式桥梁，横跨于溪潭上，规模颇具壮观，系福鼎市现存唯一的木构虹梁式廊屋桥， 为县级文物保护单位。",
            "瑶列岛国家级海洋公园位于福鼎巿东南部，总面积6783公顷，其中重点保护区3330公顷，适度利用区2186公顷，预留区1267公顷。福瑶列岛由大箭山、小箭山、鸳鸯岛、银屿、鸟屿等11个岛屿和九个礁石组成，总面积24.5平方公里。古称福瑶列岛，意即\"福地、美玉”。其海洋生态旅游度假区包括天马公路、天湖原始生态别墅区、大使澳休闲区、高速游轮等。2016年11月9号，国家海洋局批准建立3个国家级海洋特别保护区、10个国家级海洋公园，宁德市福瑶列岛获批建立国家级海洋公园。",
            "资国寺为闽东千年古刹，也是福鼎市原六大寺之一。现总体面积 1000多亩，常住僧众近200人。位于福鼎市东南郊，距城五公里的莲蜂山上，建于唐咸通元年( 860 )。寺为唐冠庄叶庞兄弟所建，并施舍周围所有山场。宋大全祖师重兴，相传有九井十三墩，为鼎盛时期。明重建法堂，明中期本邑玉塘夏姓一支系施白金一百两助建大雄宝殿。清乾隆年间，重修祖师殿。光绪十六年( 1890 )，兴建伽蓝殿。",
            "杨家溪位于霞浦县牙城镇境内。拥有纬度超北的古榕树群和江南超大的纯枫叶林。其中值得一提的是一株“榕树王”，它的树龄已有800多年，树干周长12.6米，冠幅直径51米，高30米，树干中空，有7个洞口，洞内可容数人。每当早晨的阳光斜斜地洒进树林，斑驳的树影在光影的晃动下展示着它的魔法，营造了一种如梦似幻的感觉。",
            "罗汉溪，源于福建省霞浦县柏洋乡洋里土勃头村，呈西北—东南流向。溪流经横江、溪西、洋沙溪，于下村汇集桐油溪支流，经百步溪，出水磨坑、大桥头入后港海。其主要支流桐油溪，源于水门乡百笕村，流域面积42平方公里，河道长度17公里。罗汉溪是霞浦县三大河流之一，蕴含丰富的水资源，是霞浦县最重要的集储水发电、农业灌溉、城市饮用水为一体的水源河流。罗汉溪又是具有独特山水景色的河流，第四纪冰川遗迹冰臼、巨石滩、峡谷、岩崖瀑布、两岸山景，绘就秀丽的山水画卷，有着重要的旅游观光价值。",
            "位于霞浦县城东北5公里处，在古代曾为重要的海港，因其海岸山石呈赤红色而得名。",
            "霞浦松山天后圣母行宫，建于北宋天圣年间，历史上别称「历史上别称「妈祖行宫」、「靖海宫」、「阿婆宫」，是继湄洲妈祖祖庙之后的第一个天后圣母行宫，是八闽创建较早，声名较着的祷祀“海峡和平女神”宫庙。素有「行宫之尊」的美誉。",
            "“空海大师纪念堂”是由中日双方出资于1994年5月21日建成的。它与1984年在西安青龙寺遗址兴建的“惠果、空海大师纪念堂”遥相辉映。绿草如茵宽广平坦的空埕右侧竖着的两块石碑，刻着建盖该堂经过和有关单位及个人芳名的碑文；左侧也对称地竖着两块石碑，一个是新居祐政先生的《赤岸镇赞歌》七言排律一首，另一个是高野山大学教授静慈圆写的“重访赤岸”七言绝诗四首：“高野残香冻未消，闽江桃杏正娇娇，兹行不为寻芳去，开祖苦心共此朝；求法不辞千里遥，漂流赤岸骨心销。",
            "滩涂，是陆地和大海之间的纽带。现代汉语词典是这么解释的：河流或海流夹杂的泥沙在地势较平的河流入海处或海岸附近沉积而形成的浅海滩。仅福建省就有近300万亩的滩涂，在漫长的岁月长河中，经过无数次的潮起潮落，滩涂形成了自己特有的景观，尤其是这些年迅速发展起来的近海滩涂养殖，那些渔排木屋，那些小舟鱼网，那些浮标竹竿，随着潮水的涨退，变幻着无穷的组合，吸引来无数摄影人的目光。",
            "霞浦县松港街道北岐村海边风光如画，影友如织。霞浦滩涂风光旖旎，2012年被《数码摄影》杂志列为“中国最值得拍摄的80个绝美之地”之一，并成为22大摄影胜地之一被重点推荐，每年前来拍摄滩涂风光的国内外影友多达20多万人次。",
            "霞浦全县14个乡镇有10个靠海，海洋优势尤为突出，境内海岸线长404公里，占全省八分之一，浅海滩涂面积104万亩，港湾众多，岛屿星罗棋布，有大小岛屿196个，港口138个。霞浦的三沙港距台湾的基隆港126港里，渔船朝发夕至，而且三沙与台湾语言相通，习俗相近。",
            "小皓的海滩是摄影者来到霞浦一定要前往的一站，潮水的涨落、季节的变换、月落日升，小皓也在每天以崭新的姿态呈现出她的五彩斑斓。同样的潮水，不同样的光影，同样的劳作，不同样的心情，摄影者总能创作出无尽的精美作品。小皓主要以沙质滩涂为主，当在顺光位置拍摄时能看到山下那一块块巨大的金黄滩涂随波而变，在逆光位置观赏这片滩涂时，镜头中那一条条从沙滩上流淌过的溪水，化作蜿蜒曲折的滩涂动脉，闪烁着迷人的银色光芒，令人兴奋不已。",
            "溪水流程20多公里，清澈见底，其间潭、濑、滩交替分布。山绿水碧，稀有树木花早异彩纷呈，樟树、枫树、榕树、花竹、万竹、金竹、杜鹃、黄杨、芦苇成片分布。“文广断船”、“金龟戏曾鳖”、“观音坐莲”等30多处人文景观，栩栩如生，维妙维肖。溪中盛产香鱼、龙鳗、毛蟹、甲鱼、鲤鱼等淡水珍品，还有鸳鸯、绶带、水獭等珍稀动物。"
    };
    private String[] dizhi ={"蕉城区","蕉城区","蕉城区","蕉城区","蕉城区","蕉城区","蕉城区","蕉城区","蕉城区",
            "古田县","古田县","古田县","古田县","古田县","古田县","古田县","古田县","古田县","古田县","古田县",
            "屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县","屏南县",
            "周宁县","周宁县","周宁县","周宁县","周宁县","周宁县","周宁县","周宁县","周宁县",
            "寿宁县","寿宁县","寿宁县","寿宁县","寿宁县","寿宁县","寿宁县","寿宁县","寿宁县","寿宁县",
            "福安市","福安市","福安市","福安市","福安市","福安市","福安市","福安市","福安市","福安市","福安市",
            "柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县",
            "福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市","福鼎市",
            "霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县"

    };
    TextView quyuname;
    ImageView fanhui;
    int f = 1;
    int count=0;
    EditText search_edit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhujiemian);
        name_receive = Result.getResult();
        fanhui = findViewById(R.id.imageView2);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ZhujiemianActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        quyuname =findViewById(R.id.didian);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                   name_receive = connect.dizhiname(1);
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        quyuname.setText(name_receive);
        if(name_receive.equals("苏州市")){
            name = suz;
            icons = szicons;
            buy = szbuy;
            introduces = szintroduction;
        }
        if(name_receive.equals("南京市")){
            name = nanj;
            icons = njicons;
            buy = njbuy;
            introduces = njintroduction;
        }
        if(name_receive.equals("扬州市")){
            name = yangz;
            icons = yzicons;
            buy = yzbuy;
            introduces = yzintroduction;
        }
        if(name_receive.equals("杭州市")){
            name = hangz;
            icons = hzicons;
            buy = buy;
            introduces = hzintroduction;
        }
        if(name_receive.equals("重庆市")){
            name = chongq;
            icons = cqicons;
            buy = cqbuy;
            introduces = cqintroduction;
        }
        if(name_receive.equals("长沙市")){
            name = changs;
            icons = csicons;
            buy = csbuy;
            introduces = csintroduction;
        }
        if(name_receive.equals("西安市")){
            name = xian;
            icons = xaicons;
            buy = xabuy;
            introduces = xaintroduction;
        }
        if(name_receive.equals("香港")){
            name = xiangg;
            icons = xgicons;
            buy = xgbuy;
            introduces = xgintroduction;
        }
        if(name_receive.equals("澳门")){
            name = aom;
            icons = amicons;
            buy = ambuy;
            introduces = amintroduction;
        }
        mRecyclerView = findViewById(R.id.recycle);
        mAdapter = new HomeAdapter();
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
//                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ZhujiemianActivity.this));
        mRecyclerView.setAdapter(mAdapter);
        mrecycleview = findViewById(R.id.recycletwo);
        madapter = new HomeAdapterTwo();
        mrecycleview.setLayoutManager(new LinearLayoutManager(ZhujiemianActivity.this));
        mrecycleview.setAdapter(madapter);
        Button search_button = findViewById(R.id.search_button);
        search_edit = findViewById(R.id.search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int a=0;a<nametwo.length;a++){
                    if(search_edit.getText().toString().equals(nametwo[a])){
                        count = a;
                        break;
                    }
                }
                final int count1 = count;
                if(!"".equals(search_edit.getText().toString())){
                    if(nametwo[count1].equals(search_edit.getText().toString())){
                        Intent intent = new Intent();
                        intent.setClass(ZhujiemianActivity.this, DetailsActivity.class);
                        intent.putExtra("detail_iv", iconstwo[count1]);
                        intent.putExtra("detail_name", nametwo[count1]);
                        intent.putExtra("detail_buy",buytwo[count1]);
                        intent.putExtra("detail_introduce", introducestwo[count1]);
                        intent.putExtra("f",f);
                        intent.putExtra("name",dizhi[count1]);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ZhujiemianActivity.this,"查找不到！",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ZhujiemianActivity.this,"输入内容为空，请重新输入！！",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from( ZhujiemianActivity.this).inflate(R.layout.recycle_item,parent,false));
            return holder   ;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.iv.setImageResource(icons[position]);
            holder.buy.setText(buy[position]);
            holder.name.setText(name[position]);
            holder.introduce.setText(introduces[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ZhujiemianActivity.this,DetailsActivity.class);
                    intent.putExtra("detail_iv",icons[position]);
                    intent.putExtra("detail_name",name[position]);
                    intent.putExtra("detail_buy",buy[position]);
                    intent.putExtra("name",name_receive);
                    intent.putExtra("detail_introduce",introduces[position]);
                    intent.putExtra("f",f);
                    startActivity(intent);
                    finish();
                }
            });
        }



        @Override
        public int getItemCount() {
            return name.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            ImageView iv;
            TextView introduce;
            TextView buy;
            public MyViewHolder(View view){
                super(view);
                buy = view.findViewById(R.id.buy);
                name = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                introduce=(TextView)view.findViewById(R.id.introduce);
            }
        }
    }
    class HomeAdapterTwo extends RecyclerView.Adapter<HomeAdapterTwo.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from( ZhujiemianActivity.this).inflate(R.layout.recycle_itemfirst,parent,false));
            return holder   ;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.iv.setImageResource(qitaicons[position]);
            holder.name.setText(qita[position]);
            holder.buy.setText(qitabuy[position]);
            holder.introduce.setText(qitaintroduce[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ZhujiemianActivity.this,DetailsActivity.class);
                    intent.putExtra("detail_iv",qitaicons[position]);
                    intent.putExtra("detail_name",qita[position]);
                    intent.putExtra("detail_buy",qitabuy[position]);
                    intent.putExtra("detail_introduce",qitaintroduce[position]);
                    intent.putExtra("f",f);
                    intent.putExtra("name",qitaname[position]);
                    startActivity(intent);
                    finish();
                }
            });
        }



        @Override
        public int getItemCount() {
            return qita.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            ImageView iv;
            TextView introduce;
            TextView buy;
            public MyViewHolder(View view){
                super(view);
                buy = view.findViewById(R.id.buy);
                name = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                introduce=(TextView)view.findViewById(R.id.introduce);
            }
        }
}}

