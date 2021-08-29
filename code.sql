-- SELECT * From (SELECT * 
--                   FROM Flights 
--                   WHERE  NOT (flightId IN  
--                   (SELECT flightId  
--                   FROM CancelledFlights 
--                   WHERE cancelledDate = '2021-09-11')))
-- WHERE ( activeTill>='2021-09-11' OR activeTill IS NUll) AND travelFrom='karachi' AND travelTo='lahore';

-- SELECT * From (SELECT * 
--                   FROM Flights 
--                   WHERE  NOT (flightId IN 
--                   (SELECT flightId 
--                   FROM CancelledFlights
--                   WHERE cancelledDate = '2021-09-11')))
-- 				  WHERE ( activeTill>='2021-09-11' OR activeTill IS NUll) 
-- AND travelFrom='karachi' AND travelTo='lahore';

--  SELECT flightId 
--                   FROM CancelledFlights
--                   WHERE cancelledDate = '2021-09-11';

-- SELECT * FROM Flights WHERE NOT (flightId IN(SELECT flightId 
--                   FROM CancelledFlights
--                   WHERE cancelledDate = '2021-09-11')) WHERE activeTill>='2021-09-11' OR activeTill is NULL
-- 				 

-- cancel flights ke anadr jo pari hain us date par usko chor kr baq sbh 

SELECT * 
                  FROM Flights 
                  WHERE  NOT (flightId IN 
                  (SELECT flightId
                   FROM CancelledFlights 
                  WHERE cancelledDate = '2021-09-11'))