
�Ѿ���ɵ����ݣ�
�Զ���������DB��JAVA��ͼ򵥵�SQL��䡣
����ϵͳ��Ƶĺô����ڣ���ȫ��ź�������ڲ��޸�ԭ����Ļ�����ֱ�ӿ�������µĴ������������:

�����Ҫ�µ����ݿ⵽JAVA�ֶεĶ�Ӧ��ϵ��
��ʵ��ʹ��ǰ����
ColumnType2JAVATypeUtils.registerCorresponding(String dbFieldType, String javaFieldType);


�����Ҫ�µ����ݿ⵽JAVA�ֶε����ɹ���
�½�һ���̳в�ʵ��BaseFieldProcessor�������
Ȼ����ʵ��ʹ�õ���
JAVAFieldProcessorManager.registerFieldProcessor(String name, BaseFieldProcessor fieldProcessor);
ToolsHelper.setJavaFieldregulations(String javaFieldregulations);

�����Ҫ����һ���µ�DBʵ�֣��ڵ���֮ǰ������²�����
�����ĸ�ʵ����ISQLProcess����
Ȼ����ʵ��ʹ��ǰ����
DeleteProcessorManager.registerDeleteProcessor(String name, ISQLProcess deleteProcessor);
InsertProcessorManager.registerInsertProcessor(String name, ISQLProcess insertProcessor);
UpdateProcessorManager.registerUpdateProcessor(String name, ISQLProcess updateProcessor);
SelectProcessorManager.registerSelectProcessor(String name, ISQLProcess selectProcessor);
ToolsHelper.setDbType(String dbType);
�����nameҪ��dbType+":"+SQL���ౣ��һ��.
����DB2 + Ibatis�Ļ���
DeleteProcessorManager.registerDeleteProcessor("DB2:Ibatis", deleteProcessor);
InsertProcessorManager.registerInsertProcessor("DB2:Ibatis", insertProcessor);
UpdateProcessorManager.registerUpdateProcessor("DB2:Ibatis", updateProcessor);
SelectProcessorManager.registerSelectProcessor("DB2:Ibatis", selectProcessor);
ToolsHelper.setDbType("DB2");
ToolsHelper.setSqlType("Ibatis");

DBToolsFactory��һ��������DBToolsBuilder�ǹ��ˣ�table��ԭ�ϡ�
����������������������ҪBaseDataProcess��ԭ������ȡ��Ȼ�󾭹�
deleteProcessor,insertProcessor,updateProcessor,selectProcessor
�ĵ�����
����������У����˻�����Լ���ʹ��ϰ��ѡ��ͬ�Ĺ��ߡ���Ȼ����㲻���⣬������
�������ߡ�


��һ������
1.���VO��Controller��Manager��Ŀ¼���ã�ֱ�����ɸ����ļ���


    
    
    
    
