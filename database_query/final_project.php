<?php
// variable for form error message
$msg = "";
$semester_year = "";

$dsn      = "mysql:host=localhost;dbname=college_cherny";  //data source host and db name
$username = "root";
$password = "";


// Create connection
$conn = new PDO($dsn, $username, $password); // creates PDO object

// Check connection using try/catch statement

try  {
	$conn = new PDO($dsn, $username, $password);
//	echo "Connection is successful<br><br>";
}

catch (PDOException $e) {
       $error_message = $e->getMessage();
	echo "An error occurred: $error_message" ;
}
	// sql statement set up 
    $sql  = "SELECT CONCAT(me.lname, ', ', me.fname) AS Name, "; 
	$sql .= "sc.organization AS organization, "; 
	$sql .= "sc.amount AS amount, "; 
	$sql .= "sc.semester AS semester, "; 
	$sql .= "sc.year AS year "; 
	$sql .= "FROM "; 
	$sql .= "members me "; 
	$sql .= "JOIN scholarships_students ss ON (ss.student_id = 
me.student_id) "; 
	$sql .= "JOIN scholarships sc ON (ss.scholarship_id = 
sc.scholarship_id) "; 
	$sql .= "ORDER BY year DESC, semester, amount DESC, lname "; 
	
//$sql = "SELECT  
//        CONCAT(me.lname, ', ', me.fname) AS Name, 
//        sc.organization AS organization,  
//        sc.amount AS amount,          sc.semester AS semester,  
//        sc.year AS year        
//	FROM 
//        members me 
//        JOIN scholarships_students ss ON (ss.student_id = me.student_id)  
//        JOIN scholarships sc ON (ss.scholarship_id = sc.scholarship_id)
//		
//                 ORDER BY year DESC, semester,  amount DESC, lname ";     

// prepare statement
$statement = $conn->prepare($sql);

// execute (create) the result set
$statement->execute();

// row count
$rowcount = $statement->rowCount();

// just to test
//echo "Row count for Table is " . $rowcount . "<br><br>";

$statement = $statement->fetchAll(PDO::FETCH_ASSOC);

//	print_r($statement);

// SQL for year

// sql statement set up 
$sql2 = "SELECT DISTINCT year FROM scholarships";
$statement2 = $conn->prepare($sql2);

// execute (create) the result set
$statement2->execute();

$rowcount2 = $statement2->rowCount();

// just to test
//echo "Row count for year is " . $rowcount2 . "<br>";

$statement2 = $statement2->fetchAll(PDO::FETCH_ASSOC);

//	print_r($statement);


// Form Postback
// retrieve from values
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	// retrieve form values
	$year = $_POST["year"];
	$semester = $_POST["semester"];
	
//	echo "year is $year <br>";
//	echo "semester is $semester <br>";
	
	$semester_year = "$semester $year";
	
	// user must make 2 choices
	if ($year == "none" || $semester == "none") {
		$msg = "Please choose a year and a semester";
	}
	else {
		$sql  = "SELECT CONCAT(me.lname, ', ', me.fname) AS Name, "; 
		$sql .= "sc.organization AS organization, "; 
		$sql .= "sc.amount AS amount, "; 
		$sql .= "sc.semester AS semester, "; 
		$sql .= "sc.year AS year "; 
		$sql .= "FROM "; 
		$sql .= "members me "; 
		$sql .= "JOIN scholarships_students ss ON (ss.student_id = 
me.student_id) "; 
		$sql .= "JOIN scholarships sc ON (ss.scholarship_id = 
sc.scholarship_id) ";
		$sql .= " WHERE semester = :sem and year = :yr ";
//		$sql .= " WHERE semester = '$semester' and year = '$year' ";
		$sql .= "ORDER BY year DESC, semester, amount DESC, lname "; 
		
		// prepare statement 
		$statement = $conn->prepare($sql);
		
		//execute (create) the result set execute([':name' => 'David', ':id' => $_SESSION['id']]);
		$statement->execute([":sem" => "$semester", ":yr" => "$year"]);
		// statement->execute();
		
		// row count
		$rowcount = $statement->rowCount();
		
		// just to test
//		echo "Row count for new table is " . $rowcount . "<br><br>";
		
		$statement = $statement->fetchAll(PDO::FETCH_ASSOC);
	}
	
} // server request check
?>



<!DOCTYPE html>
<!-- Evelina Cherny -->

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Displaying Data from the Database</title>

    <style>
        body {
            font-family: arial, sans-serif;
            font-size: 100%;
        }   

         h1 {
            text-align: center;
            font-size: 1.5em;
        }

         h2 {
            margin-bottom: 20px;
            text-align: center;
            font-size: 1.25em;
        }    


          td {
            border: 1px solid #000; 
            padding: 10px 
            width: 300px;
			text-align: left;	  
        }

        th {
            background: #898989;
            color: #fff;
            height: 20px;
            padding: 10px;
            font-size: 1.2em;
/*            width: 33%;*/
        }


        table {
            border-collapse: collapse;
            border: 2px solid #000;
/*            width: 600px;*/
            margin-left: auto;
            margin-right: auto;
			text-align: center;
			margin-bottom: 30px
        }

        tbody tr:nth-of-type(odd) {
            background: #eee;
        }
    </style>
</head>

<body>
   
 <header>  
    <h1><?php print $rowcount ?> Scholarships Offered <?php print $semester_year?></h1>
 </header>     
 
 
 <?php
	
 // check to make sure we have records returned
if ($rowcount != 0){
    
    // header row of table
  	echo "<table>\n\r";  
  	echo "<tr>\n\r"; 
  	echo "<th>Student Name</th>\n\r"; 
  	echo "<th>Scholarship</th>\n\r"; 
  	echo "<th>Amount</th>\n\r"; 
	echo "<th>Semester</th>\n\r"; 
	echo "<th>Year</th>\n\r"; 
  	echo "</tr>\n\r\n\r"; 
    
    // body of table number_format($myNumber, 2)
 foreach($statement as $row) {
	 echo "<tr>\n\r";
	 echo "<td>" . $row["Name"] . "</td>\n\r";
	 echo "<td>" . $row["organization"] . "</td>\n\r";
	 echo "<td class=ra>" . number_format($row["amount"], 2) . "</td>\n\r";
	 echo "<td>" . $row["semester"] . "</td>\n\r";
	 echo "<td>" . $row["year"] . "</td>\n\r";
	 echo "</tr>\n\r\n\r";         
 } // end foreach
    
    // end table
   echo "</table>\n\r\n\r";
    
}  // end if 
     
else {
     echo "<p><center>Sorry, there were no scholarship offered for $semester $year</center></p>\n\r";
} // end else


// check to make sure we have records returned for select, year 
if ($rowcount2 != 0){ 
	// begin form and select for year    
	echo "<form action='". $_SERVER['PHP_SELF'] . "' method='post'>\n\r";     echo "<label for='year'>Select a Year:</label>\n\r"; 
	echo "<select name='year' id='year' required>\n\r";    
	echo "<option value=''>Make a Selection</option>\n\r"; 
         
    // note the positioning of the quotations in <option> 
	foreach($statement2 as $row) {     
		echo"<option value='" . $row["year"] . "'>" . $row["year"] . "</option>\n\r";          
    } // end foreach  
	
	// end select
 	echo "</select>\n\r<br><br>\n\r"; 
//	echo "input type='submit' value='Display Scholarship Results'>\n\r";
//	echo "</form>\n\r";
	
	// select for semester 
   echo "<label for='semester'>Select a Semester:</label>\n\r";  
   echo "<select name='semester' id='semester' required>\n\r"; 
   echo "<option value=''>Make a Selection</option>\n\r";     
   echo "<option value='Fall'>Fall</option>\n\r";   
   echo "<option value='Spring'>Spring</option>\n\r";  
   echo "</select>\n\r<br><br>\n\r";     
 
	// submit button and end form   
	echo "<input type='submit' value='Display Scholarship Results'>\n\r";   
	echo "</form>\n\r";      
	
} // end if
else {
	echo "Sorry, there were no results";
} // end else
	
// close the connection
$conn = null;        

?>

</body>
</html>
