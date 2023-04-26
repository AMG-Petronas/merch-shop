Feature: accesories are received on accessory
  Scenario: client makes call to GET /accessory
    When the client calls /accessory
    Then the client receives status code of 200
    And the client receives response [{"id":"0f2cca1e-d9dc-42f2-a8ee-65f029f139f9","name":"Cana test test","description":"Descriere cana","colour":"Albastru","driver":"Hamilton","price":15.0}]