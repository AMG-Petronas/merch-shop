Feature: greeting is displayed on clothes
  Scenario: client makes call to GET /clothes
    When the client calls /clothes
    Then the client receives status code of 200
    And the client receives response [{"id":"11123","title":"TEst title","description":"Test description","material":"Material test","size":"Large","price":100},{"id":"1112113","title":"TEst title","description":"Test description","material":"Material test","size":"Large","price":100},{"id":"11112322113","title":"TEst title","description":"Test description","material":"Material test","size":"Large","price":100}]
