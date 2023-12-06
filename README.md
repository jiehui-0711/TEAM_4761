# 檔案介紹

data:訓練集資料.txt

data1驗證集資料.tsv

data2驗證集資料.txt

data3競賽集資料.txt

test/in1:訓練集標注檔

test/in2:測試集標注檔

ans:分類標注檔的資料

loca:標注檔內容所對應的文字及位置

set:將標注檔中各項類別的答案以集合的形式存入txt檔案中

## src（程式碼)

>runa.java:主要分析程式，修改data變數為"data","data2","data3"後 執行即可分析該資料集的資料（執行後會輸出鰾住擋到out資料夾）
---
>model.java分類答案：將答案依照各自的類別存入ans資料夾的各個txt檔案中，以便分析資料
>
>修改a變數即可分析該類別資料
---
>model1.java 搜索答案位置:讀取ans檔案中的每一項標注檔資料,並在資料集中搜尋該資料所對應的位置，存入"loca/(類別)_1.txt" 檔案中，以便分析資料
>
>ans資料夾中其中一類別的檔案複製到loca資料夾中即可並修改a變數為該列別即可執行
---
>model2.java 建立答案集合:將標注檔中各項類別的答案以集合的形式存入set資料夾的txt檔案中
>
>修改a變數即可執行
---
>model3.java 搜尋關鍵字: 逐一查詢所有資料，查看檔案中是否有search陣列的關鍵字（有則輸出該行資料和位置）
>
>將想查詢的變數放入search變數中即可查詢
---
>mode5.java比對runa程式輸出得標住檔及lodalab下載的標住檔內容差距 並計算正確率
>
>model5 比對訓練集正確率:runa-data變數改為"data"並執行後輸出標注擋到out.txt檔案 即可執行model5，可調整a變數選擇需要分析的類別
>
>model51 比對測試集正確率:runa-data變數改為"data2"並執行後輸出標注擋到out.txt檔案 即可執行model51，可調整a變數選擇需要分析的類別
---
>model6 以文字順序排序runa產生的標住檔資料
>
>runa執行後輸出標注擋到out.txt檔案 即可執行

