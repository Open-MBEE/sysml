import requests
import json
import getpass
import base64

username = raw_input('Username:')
pw = getpass.getpass('Password:')

encoded_data = base64.b64encode(username + ':' + pw)

auth_str = 'Basic ' + encoded_data
headers = {
   'Authorization': auth_str
}

# Bike model
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-47f86fd9-9667-471f-aa8a-9a2f22164944?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('Bike_Project.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# MBSE Analyzer profile
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-390370e8-5c4f-4611-975e-62bc48a4fabc?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('MBSE_Analyzer.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# SI ValueType Library
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-9c24b9c85bda414753741fd1a4d5a3b?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('SI_Valuetype_Library.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# SI Definitions
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-b7fb4c3474b8fb3c284b1485dfcf2991?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('SI_Definitions.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# SI Specializations
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-352af929b97e226b626808513753ede?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('SI_Specializations.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# QUDV Library
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-8b74b2603d10895eeafd7d18da1fcf6?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('QUDV.json', 'w')
f.write(result.text.encode('utf8'))
f.close()	
    
# SysML Profile
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-44892a3bf693c0a47eb2f3bf10930f?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('SysML.json', 'w')
f.write(result.text.encode('utf8'))
f.close()	    

# MD Customization for SysML Profile
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-9b4d2b1641e6203934d95e7bde5fe08?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('MD_Customization_for_SysML.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# MD Customization for view and viewpoint
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-66f2967d-7023-4926-bd89-de7e2f1a12ec?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('MD_Customization_for_View_Viewpoint.json', 'w')
f.write(result.text.encode('utf8'))
f.close()

# SysML Extensions
url = "https://ems-stg.jpl.nasa.gov/alfresco/service/workspaces/master/elements/PROJECT-ID_5_9_12_2_36_25_PM__3355cc91_137159f37b3__5c3b_sscae_cmr_137_78_160_100?recurse=true"
result = requests.get(url, headers=headers)
print result.status_code
if result.status_code == 200:
   structure_json = json.loads(result.text)
f = open('SysML_Extensions.json', 'w')
f.write(result.text.encode('utf8'))
f.close()	 


    


