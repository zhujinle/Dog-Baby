<template>
    <div class = "body" >
        <div class="news-header">
            <div class="content text-center" style="background-image: url(https://s2.loli.net/2024/01/04/4ZM5H2JSQ8leKNf.png);">
                <div class="introduce animated fadeInUp">
                    <div class="title">Dog-Baby</div>
                    <div class="mate">好好学习，天天向上</div>
                </div>
            </div>
        </div>
        <el-main style="width: 75%;margin: 0 auto ;">
            <el-carousel height="500px" interval="10000">
                <el-carousel-item v-for="item in newsList" :key="item.nid" :name="item.title">
                    <el-row>
                        <el-col :span="12" >
                            <img  class="carouselImg" :src="item.img" alt="News Thumbnail" style="width:100%; height:100%"/>
                        </el-col>
                        <el-col class="carouselText" :span="12">
                            <h1 class="carouselItemTitle">{{ item.title }}</h1>
                            <h3 class="carouselItemSummary">{{ item.summary }}</h3>
                        </el-col>
                    </el-row>
                </el-carousel-item>
            </el-carousel>
            <!-- 第一个大图 -->
            <div class="news-container">
                <div class="news-list">
                    <div class="news-item" @click="goToNews(newsList[0])">
                        <div class="first-news-thumbnail">
                            <img :src="newsList[0].img" alt="News Thumbnail" />
                        </div>
                        <div class="news-content">
                            <h2 class="news-title">{{ newsList[0].title }}</h2>
                            <p class="news-summary">{{ newsList[0].summary }}</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 后面的小新闻 -->
            <div class="news-category">
                <h3 class="category-title">分类新闻</h3>
                <ul>
                    <li v-for="news in newsList.slice(1)" :key="news.nid" class="category-item" @click="goToNews(news)">
                        <div class="category-thumbnail">
                            <img :src="news.img" alt="News Thumbnail" />
                        </div>
                        <div class="category-content">
                            <h4 class="category-news-title">{{ news.title }}</h4>
                            <p class="category-news-summary">{{ news.summary }}</p>
                        </div>
                    </li>
                </ul>
            </div>
        </el-main>
        <el-footer>
            <div class="text-center">Dog-Baby ©2024 Created by PeterPig&zzx&zcy with ❤</div>
        </el-footer>
    </div>
</template>

<script>
export default {
    data() {
        return {
            newsList: []
        };
    },
    mounted() {
        this.getNewsList();
    },
    methods: {
        async getNewsList() {
            // Make an API call to get the news list
            // Replace 'getNewsList' with the actual API endpoint
            const res = await this.$http.get('getNewsList?type=5')
            if (res.data.statusCode !== 200) return this.$message.error('网络错误！')
            console.log(res.data.data)
            this.newsList = res.data.data
        },
        goToNews(news) {
            window.open('#/detail/'+ news.nid, '_blank');
        }
    }
};
</script>

<style scoped>
.category-title {
    font-size: 36px; /* 根据需要调整字体大小 */
  font-weight: bold; /* 设置字体加粗 */
  padding-left: 50px; /* 根据需要调整向右移动的距离 */
}

.category-news-title {
  line-height: 0; /* 根据需要调整行高的倍数 */
  font-size: 30px; /* 设置字体大小为30像素，根据需求调整 */
  font-weight: bold; /* 设置字体加粗 */
  margin-top: 15px; /* 根据需要调整上边距的大小 */
}
.category-content {
  margin: 0 50px; /* 10像素的水平外边距，上下外边距保持为0 */
  padding: 10px 0; /* 10像素的垂直内边距，左右内边距保持为0 */
}

.news-container {
    margin: 0 auto;
    padding: 20px;
}
.news-title {
    font-size: 200px;
    font-weight: bold;
}

.news-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.news-item {
    width: 100%;
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    padding: 0px;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
}
.first-news-thumbnail {
    width: 50%; /* 占据剩余空间 */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
.news-thumbnail img {
    width: 50%;
    height: auto;
    border-radius: 5px;
}

.news-content {
    width: 50%;
    padding-left: 300px; /* 为文字内容留出一些间距，根据需要调整 */
}

.news-title {
    font-size: 36px;
    font-weight: bold;
    margin-bottom: 10px;
}

.news-summary {
    font-size: 20px;
    color: #666;
    margin-bottom: 10px;
}

.news-category {
    margin-top: 20px;
}

.category-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    cursor: pointer;
}

.category-thumbnail img {
    width: 100px;
    height: auto;
    border-radius: 5px;
}
.content {
    position: relative;
    height: 300px;
    background-position: center center;
    background-size: cover;
    width: 100%;
}

.introduce {
    position: absolute;
    top: 20%;
    z-index: 10;
    width: 100%;
    color: #fff;
    text-shadow: 2px 2px 2px #666;
    cursor: default;
    font-weight: 350;
    font-size:80px;
    font-family: -apple-system,BlinkMacSystemFont,opensans,Optima,"Microsoft Yahei",sans-serif;
    line-height: 1.8;
    overflow-x: hidden;
    text-align: center!important;
}

.mate{
    font-size:32px;
}
.el-carousel img {
    width:100%;
    height:100%;
}
.el-carousel{
    padding: 20px;
}
.carouselText{
    padding: 15px;
}
.carouselItemTitle {
  font-size: 64px; /* 根据需要调整字体大小 */
  font-weight: bold; /* 设置字体加粗 */
}
.carouselItemSummary {
  font-size: 24px; /* 根据需要调整字体大小 */
  width: 120%; /* 根据需要调整文本框宽度 */
  /* 其他样式属性如 font-weight, font-style, font-family 等可以根据需要添加或修改 */
}
.text-center{
    text-align: center!important;
}

</style>
