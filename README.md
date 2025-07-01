<p align="center">
<img width="10%" title="Wikipedia" src="/media/icons/wikipedia.png" href="https://ru.wikipedia.org/">
</p>

# Проект по автоматизации тестовых сценариев для [Wikipedia](https://ru.wikipedia.org/)

## 📜 Содержание

- [Используемый стек](#computer-используемый-стек)
- [Реализованные проверки](#-реализованные-проверки)
- [Запуск автотестов](#️-запуск-автотестов)
- [Запуск тестов из терминала](#запуск-тестов-из-терминала)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Пример Allure-отчета](#-пример-allure-отчета)
- [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)
- [Видео примера запуска тестов в Selenoid](#-видео-примера-запуска-тестов-в-selenoid)

## :computer: Используемый стек
<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="media/icons/intellij-idea-svgrepo-com.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="media/icons/java-svgrepo-com.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://www.android.com/"><img src="media/icons/android.svg" width="50" height="50"  alt="Android"/></a>  
<a href="https://developer.android.com/studio"><img src="media/icons/androidstudio.svg" width="50" height="50"  alt="Android Studio"/></a>  
<a href="https://github.com/"><img src="media/icons/github-badge-svgrepo-com.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="media/icons/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="media/icons/gradle-svgrepo-com.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="media/icons/selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://www.browserstack.com/"><img src="media/icons/browserstack.svg" width="50" height="50"  alt="Browserstack"/></a> 
<a href="https://appium.io/"><img src="media/icons/appium.png" width="50" height="50"  alt="Appium"/></a> 
<a href="https://rest-assured.io/"><img src="media/icons/rest-assured.png" width="50" height="50"  alt="REST-assured"/></a> 
<a href="https://github.com/allure-framework/allure2"><img src="media/icons/allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://qameta.io/"><img src="media/icons/Allure_TestOps.svg" width="50" height="50"  alt="TestOps"/></a> 
<a href="https://www.jenkins.io/"><img src="media/icons/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://telegram.org/"><img src="media/icons/telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

Автотесты для мобильного приложения на `Android` разработаны на языке программирования `Java` с использованием фреймворков `Selenide` и `Appium`; `UIAutomator2` используется как Android драйвер.

В качестве фреймворка для запуска тестов используется `Junit5`, в качестве сборщика проекта - `Gradle`, конфигурация настроена с помощью библиотеки `Owner`.

Произведена настройка CI системы `Jenkins`, при запуске автотестов из которой выполнение тестов осуществляется в облачной ферме мобильных девайсов `Browserstack`. Для взаимодействия с `Browserstack API` используется библиотека `REST-assured`. По результатам каждого запуска автотестов создаётся `Allure` отчёт для визуализации результатов прогона.
При локальном запуске есть возможность использовать эмулятор `Android` девайса.

Реализована интеграция с `Allure TestOps` – системой тест-менеджмента для управления процессом тестирования.

После выполнения автотестов `Telegram` бот присылает сообщение с информацией о результатах запуска.

## 🔽 Реализованные проверки

#### Стартовые экраны приложения

* Проверка стартовых экранов приложения
* Пропуск стартовых экранов приложения по кнопке 'Skip'
* Пропуск стартовых экранов приложения по системной кнопке 'Назад'

#### Поиск и открытие статей

* Проверка результатов поиска статей по ключевому слову
* Проверка отрытие статьи через поиск

## 🔽 Запуск автотестов

### Запуск тестов из терминала

```
gradle android_browserstack_test
```

При выполнении данной команды в терминале IDE тесты запустятся удаленно в <code>Browserstack</code>.

## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/icons/Jenkins.svg"> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/C34-khabarov_ilya-MobileTests/)

Для запуска сборки необходимо перейти в раздел <code>Buld with parameters</code>, выбрать нужные параметры запуска
автотестов и нажать кнопку <code>Build</code>.
<p align="center">
<img title="Jenkins Build" src="media/report_media/allure1_report_example.png">
</p>
После выполнения сборки, в блоке <code>Builds</code> напротив номера сборки появятся значки <code>Allure Report</code> и <code>Allure TestOps</code>, при клике на которые откроется страница со сформированным html-отчетом и тестовой документацией соответственно.

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="media/icons/Allure.svg"> Пример [Allure-отчета](https://jenkins.autotests.cloud/job/C34-khabarov_ilya-MobileTests/17/allure)
Содержание Allure-отчета:

* Шаги теста;
* Скриншот страницы на последнем шаге;
* Page Source;
* Логи браузерной консоли;
* Видео выполнения автотеста.

<p align="center">
<img title="Allure Overview" src="media/report_media/allur1e_tests_result_example.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/icons/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет
сообщение с отчетом о прогоне тестов.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/report_media/telegram_report1.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Selenoid" src="media/icons/browserstack.svg"> Видео примера запуска тестов в Browserstack

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста

<p align="center">
<img title="Selenoid Video" src="images/video/video.gif" width="350"  alt="video">  
</p>
<p align="center">
<img title="Selenoid Video" src="images/video/video1.gif" width="350"  alt="video"> 
</p>
