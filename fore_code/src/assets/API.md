# API列表
所有命名使用驼峰准则


---

所有的页面需要检查Session Token是否存在及是否过期

地址 smelly-Tofu.peterpig.eu.org/api/V1/

### 平台API

分地址：smelly-Tofu.peterpig.eu.org/api/V1/

例如：

```Python
# Python
inport requests
url = ‘https://smelly-Tofu.peterpig.eu.org/api/V1/CheckSessionToken/’ #请求地址
data = {
‘DeviceID’ ：’PETERS-LAPTOP’
‘SessionToken’:’cilddasgfdclian123465’
} #请求内容数据
r = request.post(url,data = data)
print(r.json()) 
```

```javascript
// JS
function countTime() {
     //先通过ajax去后台请求数据，成功后携带数据进行跳转
     $.ajax({
     	url : "https://smelly-Tofu.peterpig.eu.org/api/V1/CheckSessionToken/",
        type : "POST",
        dataType : 'json',
        data : {
            ‘DeviceID’ ：’PETERS-LAPTOP’,
			‘SessionToken’:’cilddasgfdclian123465’,
        },
        success:function (data) {
        	console.log(data);
        }
     })
}
```

```javascript
//小程序
wx.request({
          url: 'https://smelly-Tofu.peterpig.eu.org/api/V1/CheckSessionToken/', //api接口，详见接入文档
          method:"POST",
          data: {
            DeviceID ：’PETERS-LAPTOP’,
			SessionToken:’cilddasgfdclian123465’
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          success (res) {
            wx.showToast({
              title:res,
              icon:'success',
              duration:1000
            })
          }
        })
```



- [ ] `POST`CheckSessionToken 检查Session Token的有效性

  输入(String)`DeviceID`/(String)`SessionToken`

  输出(String)``StatusCode`
  > 请注意，这里会返回一个StatusCode，如果是200则表示Session Token有效，如果是401则表示Session Token无效或者过期,POST JSON传参，参数样例：{"DeviceID":"12345678","SessionToken":"TVRJek5EVTJOemd4Tmpnek1Ua3dNREV4TGpRMk1UZzVNRGM9"}

- [ ] `GET`GetSessionToken 获取一个新的Session Token

  例如：http://127.0.0.1:8000/api/V1/GetSessionToken?DeviceID=123232333

  请注意，DeviceID至少8位，应通过函数生成

  输入(String)`DeviceID`

  输出(String)`StatusCode`/(String)`SessionToken`


- [ ] `POST `GetMenuList 查看用户有权限访问的菜单

  输入(String)userName/(String)SecretKey

  输出(JSON)Menulist
  
- [ ] `POST`Login 登录，获取Secret Key

  输入(String)`SessionToken`(String)`DeviceID`/(String)`UserName`/(HASH)`Password`/(String)`CAPTCHACode`(这个懒得写，默认1先)

  输出(Int)`StatusCode`/(String)`SecretKey`/(Int)`UID`
  
  此处返回401即为用户名或密码错误

### 客户层  -  Customer

分地址：smelly-Tofu.peterpig.eu.org/api/V1/Customer

传参使用multipart/form-data 不要传Json

如果错误会返回StatusCode和一个msg作为错误原因

- [x] `POST`PersonalInformationView 查看个人信息

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(String)`UserName`/(UrlString)`AvatarUrl`/(Int)`MenberStatus`/(Double)`MoneySum`/(String)`Address`/(String)`Phone`

- [x] `POST`ModifyPersonalInformation 修改个人信息

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`UserName`/(File)`AvatarUrl`/(String)`Address`/(String)`Phone`

  输出(String)`StatusCode`/(String)`UserName`

- [x] `POST`ViewShoppingCart 查看购物车

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)StatusCode/(json)CartMenber{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}

- [x] `POST`ModifyShoppingCart 修改购物车

  输入(Int)`UID`/(String)`SessionToken`/(String)`Secret Key`/(json)CartMenber{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}

  输出(String)StatusCode

- [x] `POST`MakeOrder 下单

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`OrderCheck`/(String)ShopUID

  输出(String)StatusCode/(String)OrderNumber/(String)PayUrl

  

- [x] `POST`ConfirmOrder 确认支付

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`OrderNumber`

  输出(String)StatusCode/(String)OrderNumber

- [x] `POST`OrderList 查看订单列表

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`OrderList` `{OrderNumber1,OrderNumber2.......OrderNumberN}`

- [x] `POST`CheckOrder 订单信息查看

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)OrderNumber

  输出(String)`StatusCode`(Int)`OrderNumber`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`PayStatus`/(String)`ShopName`/(String)`ShopAddress`/(String)`ShopPhone`/(String)`DeliveryStaffName`/(String)`DeliveryStaffPhone`/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/(json)`CartMenber` `{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}`/(Double)`MoneySum`

  

  

### 商家层 - Seller

分地址：smelly-Tofu.peterpig.eu.org/api/V1/Seller/

- [x] `GET`InformationView 商家信息查看

  输入(Int)`UID`(Shop's)

  输出(String)`StatusCode`(String)`ShopName`/(UrlString)`AvatarUrl`/(Int)`MenberStatus`/(String)`Address`/(String)`Phone`/(UrlString)`BusinessLicenseUrl`/

- [x] `POST`ModifyInformation 商家信息修改

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`/(String)`ShopName`/(UrlString)`AvatarUrl`/(String)`Address`/(String)`Phone`/(UrlString)`BusinessLicenseUrl`/

  输出(String)`StatusCode`/(String)`inputShopName`

- [x] `GET`MenuView 菜单信息查看

  输入(Int)`UID`(Shop's)

  输出(String)`StatusCode`(String)/(Json)`MenuList` `{[ShopID_id,Foodid1,FoodPhoto1,Money1,Discount1],[ShopID_id,Foodid2,FoodPhoto2,Money2,Discount2]......[ShopID_id,FoodidN,FoodPhotoN,MoneyN,DiscountN]}`

- [x] `POST`AddMenu 菜单信息修改

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`/(String)`FoodID`/(File)`FoodPhoto`/(float)`Money`/(Float)`Discount`

  输出(String)`StatusCode`(String)

- [x] `POST`ModifyMenu 菜单信息修改

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`/(String)`FoodID`/(File)`FoodPhoto`/(float)`Money`/(Float)`Discount`

  输出(String)`StatusCode`(String)

- [x] `POST`DashboardView 商家交易数据信息查看

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`(String)/(Double)`SumMoney`/(Double)`DailyMoney`/(Double)`MonthlyMoeny`/(Double)`Sumcustomer`/(Int)`DailyCustomerSum`

- [x] `POST`OrderList 查看订单列表

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`OrderList` `{OrderNumber1,OrderNumber2.......OrderNumberN}`

- [x] `POST`DeliveryStaffList 查看可用配送员

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`DeliveryStaffList` `{[UID1,Name1],[UID2,Name2].......[UIDN,NameN]}`

- [x] `POST`DeliveryPush 通知配送

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`/(String)`OrderNumber`/(Int)`DeliveryStaffUID`

  输出(String)`StatusCode`/(String)`OrderNumber`

- [x] `POST`CheckOrder 订单信息查询

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)OrderNumber

  输出(String)`StatusCode`(Int)`OrderNumber`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`PayStatus`/(String)`ShopAddress`/(String)`ShopPhone`/(String)`DeliveryStaffName`/(String)`DeliveryStaffPhone`/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/(Json)`CartMenber` `{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}`/(Double)`MoneySum`

### 配送层 - DeliveryStaff

分地址：smelly-Tofu.peterpig.eu.org/api/V1/Delivery_Staff

- [x] `GET` InformationView 配送员信息查看

  输入(Int)`UID`

  输出(String)`StatusCode`(String)`UserName`/(UrlString)`AvatarUrl`/(Int)`MenberStatus`/(String)`Phone`/(UrlString)HealthCertUrl

- [x] `POST  `ModifyInformation 配送员信息修改

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`UserName`/(UrlString)`AvatarUrl`/(String)`Phone`/(FILE)`HealthCertUrl`

  输出(String)`StatusCode`/(String)`UserName`

- [x] `POST ` OrderListView  订单列表查看

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`OrderList` `{OrderNumber1,OrderNumber2.......OrderNumberN}`

- [x] `POST` CheckOrder 订单信息查询

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)OrderNumber

  输出(String)`StatusCode`(Int)`OrderNumber`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`PayStatus`/(String)`ShopAddress`/(String)`ShopPhone`/(String)`DeliveryStaffName`/(String)`DeliveryStaffPhone`/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/(Json)`CartMenber` `{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}`/(Double)`MoneySum`

- [x] `POST `ModifyOrder 订单信息修改

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)OrderNumber/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/

  输出(String)`StatusCode`

- [x] DashboardView 个人收入信息查看

  输入(Int)`UID`(Shop's)/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`(String)/(Double)`SumMoney`/(Double)`DailyMoney`/(Double)`WeeklyMoeny`/(Double)`MonthlyMoney`/(Int)`DailyOrderNumber`

### 后台管理层 - Admin

分地址：smelly-Tofu.peterpig.eu.org/api/V1/Admin

- [x] UserView 用户信息查看

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`UserList` `{[UID1,UserType1],[UID2,UserType2].......[UIDN,UserTypeN]}`

- [x] SingleUserView 单个用户信息查看

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(Int)TargetUserUID

  输出(String)`StatusCode`/(Int)`UID`/(String)`PasswordHash`/(String)`UserName`/(UrlString)`AvatarUrl`/(UrlString)`License`/(Int)`MenberStatus`/(Double)`MoneySum`/(String)`Address`/(String)`Phone`/(String)UserType/(float)`MoneyDaily`/(float)`MoneyMonthly`/(Json)`Cart`/(Int)`CustomerDaily`/(Int)`CustomerSum`

- [x] ModifyUser 用户信息修改

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`UserName`/(UrlString)`AvatarUrl`/(String)`Address`/(String)`Phone`/(Int)UserType

  输出(String)`StatusCode`

- [x] MenuView 菜单信息查看

  输入(Int)`TargetShopUID`

  输出(String)`StatusCode`(String)/(Json)(Json)`MenuList` `{[Foodid1,FoodPhotoUrl1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Money2,Discount2]......[FoodidN,FoodPhotoUrln,MoneyN,DiscountN]}`

- [ ] ModifyMenu 菜单信息修改

  输入(Int)`TargetFoodID`/(String)`SessionToken`/(String)`SecretKey`/(File)`FoodPhoto`/(float)`Money`/(Float)`Discount`

  输出(String)`StatusCode`(String)

- [ ] OrderListView 订单列表查询

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`/(json)`OrderList` `{OrderNumber1,OrderNumber2.......OrderNumberN}

- [ ] `POST`CheckOrder 订单信息查询

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)OrderNumber

  输出(String)`StatusCode`(Int)`OrderNumber`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`PayStatus`/(String)`ShopAddress`/(String)`ShopPhone`/(String)`DeliveryStaffName`/(String)`DeliveryStaffPhone`/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/(Json)`CartMenber` `{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}`/(Double)`MoneySum`

- [ ] ModifyOrder 订单信息修改

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(Int)`OrderNumber`/(String)`Address`/(String)`Phone`/(String)`Notes`/(Int)`Payment` `(1:Alipay,2:Wechat,3:balance)`/(Int)`PayStatus`/(String)`ShopAddress`/(String)`ShopPhone`/(String)`DeliveryStaffName`/(String)`DeliveryStaffPhone`/(Int)`DeliveryStatus` `(0:商家未接单,1:商家已结单未分配配送,2:商家已结单已分配配送未取餐,3:配送正在店内取餐,4:配送中,5:已送达)`/(Json)`CartMenber` `{[Foodid1,FoodPhotoUrl1,Number1,Money1,Discount1],[Foodid2,FoodPhotoUrl2,Number2,Money2,Discount2]......[FoodidN,FoodPhotoUrlN,NumberN,MoneyN,DiscountN]}`/(Double)`MoneySum

  输出(String)`StatusCode`

- [ ] DeliveryPush 配送员分配

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`/(String)`OrderNumber`/(Int)`DeliveryStaffUID`

  输出(String)`StatusCode`

- [ ] DashboardView 整体信息查看

  输入(Int)`UID`/(String)`SessionToken`/(String)`SecretKey`

  输出(String)`StatusCode`(String)/(Double)`SumMoney`/(Double)`DailyMoney`/(Double)`WeeklyMoeny`/(Double)`MonthlyMoney`/(Int)`DailyCustomerSum`





---

## Status Code 定义

定义类Http Request状态码定义

- 200 OK
- 302 Move Temporarily
- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 405 Method Not Allowed
- 408 Request Timeout
- 411 Length Required
- 418 I'm a teapot
- 451 Unavailable For Legal Reasons
- 500 Internal Server Error
- 502 Bad Gateway
- 503 Service Unavailable



---

## 界面定义

100 用户功能
- 101 点菜
- 102 购物车管理

###### 200 菜单管理

- 201 食品列表

- 202 单个菜品查看

- 203 单个菜品编辑

- 204 增加单个菜品

  ###### 300 订单管理

- 301 订单列表

- 302 单个订单查看

- 303 单个订单修改

- 304 可用配送员查看

- 305 通知配送

  ###### 400 用户管理                only 管理

- 401 用户列表

- 402 单个用户查看

- 403 单个用户修改

  ###### 500 个人信息

- 501 个人信息查看

- 502 个人信息修改

- 503 个人数据看板