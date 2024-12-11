from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# Configuración del driver
desired_capabilities = {
    "platformName": "Android",
    "deviceName": "emulator-5554",  
    "app": "U2FirebaseRocioRodriguez\app\build\outputs\apk\debug\app-debug.apk",  
    "appActivity": "utez.edu.mx.P8RealtimeDatabaseActivity",
    "appPackage": "utez.edu.mx",  
    "automationName": "UiAutomator2"
}

driver = webdriver.Remote("http://localhost:4723/wd/hub", desired_capabilities)

try:
    # Esperar a que los elementos estén visibles
    wait = WebDriverWait(driver, 10)

    # Localizar y escribir en el campo de título
    title_input = wait.until(EC.presence_of_element_located((By.ID, "utez.edu.mx:id/titleEditText")))
    title_input.send_keys("Prueba")

    # Localizar y escribir en el campo de descripción
    description_input = wait.until(EC.presence_of_element_located((By.ID, "utez.edu.mx:id/descriptionEditText")))
    description_input.send_keys("Prueba en appium para integradora")

    # Localizar y hacer clic en el botón
    add_button = wait.until(EC.element_to_be_clickable((By.ID, "utez.edu.mx:id/addButton")))
    add_button.click()

    # Verificación: Puedes verificar resultados aquí, por ejemplo, comprobando si aparece en la lista.
    print("Prueba completada con éxito.")

finally:
    # Cerrar el driver
    driver.quit()
