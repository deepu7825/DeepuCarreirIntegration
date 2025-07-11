# starshipit

args[0] = Base64 String for import.
args[1] = UUID assigned by database, or WMS

---
Goals:
- Optimised: Fast/lightweight, full function complete 10 seconds.
- Maintainable: Non java users easy to navigate, good documentation.


List of APIs/Librarys/Frameworks/Requirements:
Java 18+
Maven Project: https://maven.apache.org/
JDBC/Java.sql to LCC-DB: https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
OkHttp for API calls to Starshipit: https://kong.github.io/unirest-java/


User requirements:
- Must send order information
- Must recieve back pdf of order label.


Key requirements

 

Adaptable to most freight systems using similar workflow

Standardised workflow as the software is only doing one Post and then parsing the response


Receive JSON file from the WMS or read data from a table populated by the WMS to create a JSON file to POST to the freight/carrier system


Send Post to create a shipment label with appropriate security credentials
                                                               i.      We’ll need to explore the likely range of security credentialling to understand the variations in requirements

Parse the response into a database
Trigger a cloud print command to print it to the local label printer set up in the WMS
There looks to be a variety of options from 3rd parties and printer manufacturers that will support cloud printing.
(see attached 3rd party software ebook)
https://www.satoeurope.com/products/sato-cloud-connector.php
Cloud Connect Software | Zebra
This would be a sub-project and not the primary goal but understanding the options will inform the development
Explore integrations with the following systems to inform the development.
Starshipit
- 

Shippit
JSON/REST API
- https://developer.shippit.com/api_guide/label/label-responses.html
- Returns URL for label to download.

SmartFreight
- Private API, needs requesting, via support portal.
- 

ShipStation

v1 API
Returns: Base64 PDF
- https://www.shipstation.com/docs/api/shipments/create-label/

v2 API


Sendle
- 


MachShip
Australia Post/StarTrack
Returns: URL
https://developers.auspost.com.au/apis/shipping-and-tracking/reference/create-labels