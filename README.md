# TaxReportSystem

This README.md has the next structure:

  a) Description of the project (my variant)
  
  b) Installation Instructions.
  
  c) Instructions for launching the application.
  
____________________________________________________________________________________________________________________

  a) Description of the project:
  
>>> Система Подачи Отчетов в Налоговую. Физ./Юр.лицо (далее Пользователь) регистрируется. Подает отчет (XML/JSON/Форма). 
Налоговый Инспектор принимает/отклоняет отчет (указывая причину отказа). 
Пользователь может просмотреть все поданные отчеты, причины отказа и изменять их если того потребовал Инспектор. 
Пользователь может отправлять запрос на замену Инспектора в случае неудовлетворения.

>>> The system of filing reports to the tax. Fiz. Legal entity (hereinafter referred to as User) is registered. Submit report (XML / JSON / Form). 
The tax inspector accepts / rejects the report.
The user can view all submitted reports, reasons for refusal and change them if requested by the Inspector.
The user can send a request to replace the Inspector in case of dissatisfaction.


  b) Installation Instructions:
  
      Just click "Clone or download" button on the top-left corner.
      Then choose "Download ZIP". And unpack ZIP-archive wherever you want.

  c) Instructions for launching the application:
  
      You should have installed Java and Maven on your local machine.
      Then in command prompt you have to write:
      
        1)cd [the path to directory where you unpacked ZIP-archive]
        
        2)mvn compile
        
        3)mvn clean install tomcat7: run
        
        4)write this url address prompt in your browser: http://localhost:8888
        
