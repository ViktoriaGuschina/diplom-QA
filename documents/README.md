### Запуск автоматизированных тестов.

- Клонируйте репозиторий командой git clone https://github.com/ViktoriaGuschina/diplom-QA
- Откройте папку с тестируемым приложением FMHAndroid в Android Studio как проект.
- Запустите эмулятор Pixel 9 API 35.
- В Android Studio перейдите в терминал и выполните команду: ./gradlew clean connectedAndroidTest.

### Генерация и просмотр отчетов Allure.

- Скачайте Allure2.
- Распакуйте архив и добавьте путь к папке bin из распакованного архива в переменную окружения PATH (для Windows). Проверьте корректность установки, открыв командную строку (Win + R → cmd) и выполнив allure --version.
- Для создания отчета скопируйте свежие логи с виртуального устройства в папку allure-results вашего проекта. Используйте встроенный диспетчер файлов Android Studio (Device Explorer) по пути /sdcard/googletest/test_outputfiles/allure-results. Затем запустите команду allure serve для просмотра отчета.