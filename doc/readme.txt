
已经完成的内容：
自动化构建从DB到JAVA类和简单的SQL语句。
整套系统设计的好处在于，完全解藕，并且在不修改原代码的基础上直接可以完成新的处理情况的增加:

如果需要新的数据库到JAVA字段的对应关系：
在实际使用前调用
ColumnType2JAVATypeUtils.registerCorresponding(String dbFieldType, String javaFieldType);


如果需要新的数据库到JAVA字段的生成规则：
新建一个继承并实现BaseFieldProcessor超类的类
然后在实际使用调用
JAVAFieldProcessorManager.registerFieldProcessor(String name, BaseFieldProcessor fieldProcessor);
ToolsHelper.setJavaFieldregulations(String javaFieldregulations);

如果需要增加一套新的DB实现，在调用之前完成如下操作：
定义四个实现了ISQLProcess的类
然后在实际使用前调用
DeleteProcessorManager.registerDeleteProcessor(String name, ISQLProcess deleteProcessor);
InsertProcessorManager.registerInsertProcessor(String name, ISQLProcess insertProcessor);
UpdateProcessorManager.registerUpdateProcessor(String name, ISQLProcess updateProcessor);
SelectProcessorManager.registerSelectProcessor(String name, ISQLProcess selectProcessor);
ToolsHelper.setDbType(String dbType);
这里的name要与dbType+":"+SQL种类保持一致.
如是DB2 + Ibatis的话：
DeleteProcessorManager.registerDeleteProcessor("DB2:Ibatis", deleteProcessor);
InsertProcessorManager.registerInsertProcessor("DB2:Ibatis", insertProcessor);
UpdateProcessorManager.registerUpdateProcessor("DB2:Ibatis", updateProcessor);
SelectProcessorManager.registerSelectProcessor("DB2:Ibatis", selectProcessor);
ToolsHelper.setDbType("DB2");
ToolsHelper.setSqlType("Ibatis");

DBToolsFactory是一个工厂，DBToolsBuilder是工人，table是原料。
工人在整个操作过程中需要BaseDataProcess从原料中提取，然后经过
deleteProcessor,insertProcessor,updateProcessor,selectProcessor
四道工序。
在这个过程中，工人会根据自己的使用习惯选择不同的工具。当然如果你不满意，可以让
他换工具。


下一步任务：
1.完成VO、Controller、Manager的目录配置，直接生成各类文件。


    
    
    
    
