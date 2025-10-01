### Запуск автоматизированных тестов.

- Клонируйте репозиторий командой git clone https://github.com/ViktoriaGuschina/diplom-QA
- Откройте папку с тестируемым приложением FMHAndroid в Android Studio как проект.
- Запустите эмулятор Pixel 9 API 35.
- В Android Studio перейдите в терминал и выполните команду: ./gradlew clean connectedAndroidTest.

### Генерация и просмотр отчетов Allure.

-  Установите Allure CLI. Через Homebrew (для macOS): Откройте терминал и выполните:
"brew install allure". Проверьте успешную установку: "allure --version"
- Запустите тесты
- Перенесите с эмулятора в папку проекта allure-results логи сгенерерованые после тестовю.
- Для генерации отчета из папки с результатами в терминале перейдите в ваш проект и 
выполните команду: allure generate ./allure-results -o ./allure-report --clean
- Запустите команду для открытия отчета в браузере: allure open ./allure-report