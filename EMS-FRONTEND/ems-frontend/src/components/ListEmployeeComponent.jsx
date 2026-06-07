import React, { useEffect, useState } from "react";
import { listEmployees,deleteEmployee} from "../services/EmployeeService";
import {useNavigate} from "react-router-dom";

const ListEmployeeComponent = () => {

  const [employees, setEmployees] = useState([]);
  
  const navigator = useNavigate();

  useEffect(() => {
    getAllEmployees();
  }, []);

  function getAllEmployees() {
    listEmployees()
      .then((response) => {
        setEmployees(response.data); //Updates the UI with the list of employees fetched from the backend
      })
      .catch((error) => {
        console.log(error);
      });
    }


  function addnewEmployee() {
    navigator('/add-employee');
    console.log("Add new employee");
  }

  function updateEmployee(id) {
    navigator(`/update-employee/${id}`);
    console.log("Update employee with id: " + id);
  }


  function removeEmployee(id){
    console.log("Delete employee with id: " + id);

    deleteEmployee(id)
        .then((response) => {
           getAllEmployees();
        })
        .catch((error) => {
            console.log(error);
        });
}


  return (
    <div className="container">
      <h2 className="text-center">
        <b>Employees List</b>
      </h2>
      <button className='btn btn-primary mb-2' onClick={addnewEmployee}>
        Add Employee
      </button>

      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Employee Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Id</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {
            employees.map((employee) => (
              <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.email}</td>
                <td>
                  <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}>Update</button>
                  <button className="btn btn-danger" style={{marginLeft: "10px"}} onClick={() => removeEmployee(employee.id)}>Delete</button>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default ListEmployeeComponent;