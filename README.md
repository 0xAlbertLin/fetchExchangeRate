# fetchExchangeRate
功能說明：
1.提供一批次每日 18:00 呼叫 API，取得外匯成交資料，
並將每日的美元/台幣欄位(USD/NTD)資料與日期(yyyy-MM-dd HH:mm:ss) insert 至 table/collection，
並針對批次功能寫 Unit test。

API URL：https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates

API Method：GET
