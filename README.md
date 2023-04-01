<h1 align="center"><u><i>MusalaChallenge</i></u></h1>

<p align="center">
  API Rest for Musala interview
</p>

<div align="center">
  <span>Endpoints</span> •
  <span>Examples</span>
</div>

## Instructions to Run API
- Have/Install Java 8 onwards.
- You have to open a command line on jar directory.
- Execute `mvn clean package`
- You have to write this command to run the API
  - `java -jar challenge-0.0.1-SNAPSHOT.jar`


## Endpoints

- **`/drone/create`**: Endpoint to create a new Drone.

    - **HTTP Method:** POST
    - **Parameters:** Body

        - `number` (String): Drone serial number.
        - `model` (String): Drone model.
        - `weightLimit` (Double): Drone weight limit.
        - `batteryCapacity` (Integer): Drone battery capacity.
        - `droneState` (String): Drone State.

    - **Success Answer:**

        - **HTTP Code:** 200
          - **Content:**

            ```
            {
                "number": value,
                "model": value,
                "weightLimit": value,
                "batteryCapacity": value,
                "droneState": value,
                "medications": [] 
            }
            ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "Any field can be null"
              ```
              ```
              "The entered state is not valid"
              ```
              ```
              "The entered model is not valid"
              ```
              ```
              "The weight must be lower than 500"
              ```
              ```
              "The battery capacity must be between 0 and 100 "
              ```

- **`/drone/{serialNumber}/load`**: Endpoint to load a drone with medications.

    - **HTTP Method:** PUT
    - **Parameters:** 
      - PathVariable

          - `serialNumber` (String): Drone serial number.
      
      - **Body**
        - `id` (String): Medication id.
        - `code` (String): Medication code.
        - `weight` (Double): Medication weight.
        - `image` (byte[]): Medication image.

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              {
                  "number": value,
                  "model": value,
                  "weightLimit": value,
                  "batteryCapacity": value,
                  "droneState": value,
                  "medications": [List<Medication>] 
              }
              ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "The serial number entered does not exist"
              ```
              ```
              "The drone does not allow to be loaded because of his state"
              ```
              ```
              "The weight of the medicines exceeds the limit weight of the drone"
              ```

- **`/drone/{serialNumber}/check`**: Endpoint to check medications loaded into a specified drone.

    - **HTTP Method:** GET
    - **Parameters:**
        - PathVariable

            - `serialNumber` (String): Drone serial number.

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              [
                 {
                  "id": value,
                  "code": value,
                  "weight": value,
                  "image": value, 
                 }
              ]
              ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "The serial number entered does not exist"
              ```

- **`/drone/available`**: Endpoint to get available drones to load medication.

    - **HTTP Method:** GET

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              [
                 {
                  "number": value,
                  "model": value,
                  "weightLimit": value,
                  "batteryCapacity": value,
                  "droneState": value,
                  "medications": [] 
                 }
              ]
              ```

- **`/drone/{serialNumber}/battery`**: Endpoint to get battery info for a specified drone.

    - **HTTP Method:** GET
    - **Parameters:**
        - PathVariable

            - `serialNumber` (String): Drone serial number.

    - **Success Answer:**

        - **HTTP Code:** 200
            - **Content:**

              ```
              value
              ```

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "The serial number entered does not exist"
              ```

- **`/drone/{serialNumber}/loading`**: Endpoint to change status for a specified drone to loading.

    - **HTTP Method:** PATCH
    - **Parameters:**
        - PathVariable

            - `serialNumber` (String): Drone serial number.

    - **Success Answer:**

        - **HTTP Code:** 200

    - **Errors:**

        - **HTTP Code:** 400
            - **Content:**

              ```
              "The serial number entered does not exist"
              ```



## Examples

Here you can see some examples that how you can use the API endpoints. The examples below only are for endpoints with a request body.

### Example 1
- **`/drone/create`**:
  ```
  {
  "number": "112",
  "model": "CRUISERWEIGHT",
  "weightLimit": 450.0,
  "batteryCapacity": 11,
  "droneState": "IDLE"
  }
  ```

### Example 2
- **`/drone/112/load`**:
  ```
  [
    {
        "id": "1H",
        "code": "MASFCX",
        "weight": 256.0,
        "image": null
    }
  ]
  ```

  

