# 檔案介紹
  data:訓練集資料.txt
	data1驗證集資料.txt
	data2驗證集資料.txt
	data3競賽集資料.txt
	test/in1:訓練集標注檔
	test/in2:測試集標注檔
	ans:分類標注檔的資料
	loca:標注檔內容所對應的文字及位置 
	set:將標注檔中各項類別的答案以集合的形式存入txt檔案中
	src（程式碼)
		runa.java:主要分析程式
		model.java分類答案：將答案依照各自的類別存入ans資料夾的各個txt檔案中，以便分析資料
		model1.java 搜索答案位置:讀取ans檔案中的每一項標注檔資料,並在資料集中搜尋該資料所對應的位置，存入"loca/(類別)_1.txt" 檔案中，以便分析資料
		model2.java 建立答案集合:將標注檔中各項類別的答案以集合的形式存入set資料夾的txt檔案中
		model3.java 搜尋關鍵字: 逐一查詢所有資料，查看檔案中是否有search陣列的關鍵字（有則輸出該行資料和位置）
