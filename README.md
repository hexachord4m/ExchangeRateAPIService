# ExchangeRateAPIService
SpringBootで作る為替データを提供するAPIサービス

API仕様（仮）
◆為替レート取得
http://localhost:1598/api/rate

[parameter]
target=usd|gbp|eur|zar|inr|thb
　レートの種類。カンマ区切りで複数指定可。
　usd:米ドル,  gbp:英ポンド, eur:ユーロ, zar:南アフリカランド, inr:インドルピー, thb:タイバーツ

date=yyyyMMdd
　対象日。"now"で当日。

startdate=yyyyMMdd
　開始日。対象日指定時は無効。

enddate=yyyyMMdd
　終了日。 対象日指定時は無効。

calc=sum|avg|max|min
　計算方法
　sum:合計, avg:平均, max:最大, min:最小

grouping=year|month
　計算方法指定時のグループ化対象。未指定時は全体。

[例]
本日の米ドル
http://localhost:1598/api/rate?target=usd&date=now

2015年から2017年までの米ドルとユーロの月平均
http://localhost:1598/api/rate?target=usd,eur&startdate=20150101&enddate=20171231&calc=avg&grouping=month

◆データ更新
http://localhost:1598/api/rate/update
CSVファイルを再取得する。
