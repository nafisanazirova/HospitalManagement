import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [activeTab, setActiveTab] = useState("doctors");

  // Data states
  const [doctors, setDoctors] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [patients, setPatients] = useState([]);
  const [appointments, setAppointments] = useState([]);

  // Form states
  const [doctorForm, setDoctorForm] = useState({ doctorId: null, firstName: "", lastName: "", speciality: "", phone: "", departmentId: "" });
  const [departmentForm, setDepartmentForm] = useState({ deptId: null, name: "", location: "" });
  const [patientForm, setPatientForm] = useState({ patientId: null, firstName: "", lastName: "", gender: "", dob: "", phone: "", address: "" });
  const [appointmentForm, setAppointmentForm] = useState({ apptId: null, appointmentTime: "", doctorId: "", patientId: "", reason: "" });

  const API_BASE = "http://localhost:9090/api";

  // -------------------- Fetch functions --------------------
  useEffect(() => {
    fetchDoctors();
    fetchDepartments();
    fetchPatients();
    fetchAppointments();
  }, []);

  const fetchDoctors = async () => {
    try {
      const res = await axios.get(`${API_BASE}/doctors`);
      setDoctors(res.data);
    } catch (err) { console.error(err); }
  };

  const fetchDepartments = async () => {
    try {
      const res = await axios.get(`${API_BASE}/departments`);
      setDepartments(res.data);
    } catch (err) { console.error(err); }
  };

  const fetchPatients = async () => {
    try {
      const res = await axios.get(`${API_BASE}/patients`);
      setPatients(res.data);
    } catch (err) { console.error(err); }
  };

  const fetchAppointments = async () => {
    try {
      const res = await axios.get(`${API_BASE}/appointments/full`);
      setAppointments(res.data);
    } catch (err) { console.error(err); }
  };

  // -------------------- Submit Handlers --------------------
  const handleDoctorSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      firstName: doctorForm.firstName.trim(),
      lastName: doctorForm.lastName.trim(),
      speciality: doctorForm.speciality.trim(),
      phone: doctorForm.phone.trim(),
      departmentId: doctorForm.departmentId ? parseInt(doctorForm.departmentId) : null
    };
    try {
      if (doctorForm.doctorId) await axios.put(`${API_BASE}/doctors/${doctorForm.doctorId}`, payload);
      else await axios.post(`${API_BASE}/doctors`, payload);
      resetDoctorForm();
      fetchDoctors();
    } catch (err) { console.error(err); }
  };

  const handleDepartmentSubmit = async (e) => {
    e.preventDefault();
    const payload = { name: departmentForm.name.trim(), location: departmentForm.location.trim() };
    try {
      if (departmentForm.deptId) await axios.put(`${API_BASE}/departments/${departmentForm.deptId}`, payload);
      else await axios.post(`${API_BASE}/departments`, payload);
      resetDepartmentForm();
      fetchDepartments();
    } catch (err) { console.error(err); }
  };

  const handlePatientSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      firstName: patientForm.firstName.trim(),
      lastName: patientForm.lastName.trim(),
      gender: patientForm.gender,
      dob: patientForm.dob,
      phone: patientForm.phone.trim(),
      address: patientForm.address.trim()
    };
    try {
      if (patientForm.patientId) await axios.put(`${API_BASE}/patients/${patientForm.patientId}`, payload);
      else await axios.post(`${API_BASE}/patients`, payload);
      resetPatientForm();
      fetchPatients();
    } catch (err) { console.error(err); }
  };

  const handleAppointmentSubmit = async (e) => {
    e.preventDefault();
    const [date, time] = appointmentForm.appointmentTime.split("T");
    const payload = {
      apptDate: date,
      apptTime: time,
      reason: appointmentForm.reason.trim(),
      doctorId: parseInt(appointmentForm.doctorId),
      patientId: parseInt(appointmentForm.patientId)
    };
    try {
      if (appointmentForm.apptId) await axios.put(`${API_BASE}/appointments/${appointmentForm.apptId}`, payload);
      else await axios.post(`${API_BASE}/appointments`, payload);
      resetAppointmentForm();
      fetchAppointments();
    } catch (err) { console.error(err); }
  };

  // -------------------- Edit Handlers --------------------
  const editDoctor = (doc) => setDoctorForm({ doctorId: doc.doctorId, firstName: doc.firstName, lastName: doc.lastName, speciality: doc.speciality, phone: doc.phone, departmentId: doc.department?.deptId || "" });
  const editDepartment = (dept) => setDepartmentForm({ deptId: dept.deptId, name: dept.name, location: dept.location });
  const editPatient = (pat) => setPatientForm({ patientId: pat.patientId, firstName: pat.firstName, lastName: pat.lastName, gender: pat.gender, dob: pat.dob, phone: pat.phone, address: pat.address });
  const editAppointment = (app) => setAppointmentForm({ apptId: app.appt_id, appointmentTime: `${app.appt_date}T${app.appt_time}`, doctorId: app.doctorId, patientId: app.patientId, reason: app.reason });

  // -------------------- Delete Handlers --------------------
  const deleteDoctor = async (id) => { if (window.confirm("Delete doctor?")) { await axios.delete(`${API_BASE}/doctors/${id}`); fetchDoctors(); } };
  const deleteDepartment = async (id) => { if (window.confirm("Delete department?")) { await axios.delete(`${API_BASE}/departments/${id}`); fetchDepartments(); } };
  const deletePatient = async (id) => { if (window.confirm("Delete patient?")) { await axios.delete(`${API_BASE}/patients/${id}`); fetchPatients(); } };
  const deleteAppointment = async (id) => { if (window.confirm("Delete appointment?")) { await axios.delete(`${API_BASE}/appointments/${id}`); fetchAppointments(); } };

  // -------------------- Reset Forms --------------------
  const resetDoctorForm = () => setDoctorForm({ doctorId: null, firstName: "", lastName: "", speciality: "", phone: "", departmentId: "" });
  const resetDepartmentForm = () => setDepartmentForm({ deptId: null, name: "", location: "" });
  const resetPatientForm = () => setPatientForm({ patientId: null, firstName: "", lastName: "", gender: "", dob: "", phone: "", address: "" });
  const resetAppointmentForm = () => setAppointmentForm({ apptId: null, appointmentTime: "", doctorId: "", patientId: "", reason: "" });

  // -------------------- Render --------------------
  return (
    <div className="App">
      <h1>Hospital Management</h1>
      <div className="tabs">
        {["doctors", "departments", "patients", "appointments"].map(tab => (
          <button key={tab} className={activeTab === tab ? "active" : ""} onClick={() => setActiveTab(tab)}>
            {tab.charAt(0).toUpperCase() + tab.slice(1)}
          </button>
        ))}
      </div>

      {/* -------------------- Doctors Tab -------------------- */}
      {activeTab === "doctors" && (
        <div>
          <form onSubmit={handleDoctorSubmit}>
            <input placeholder="First Name" value={doctorForm.firstName} onChange={e => setDoctorForm({ ...doctorForm, firstName: e.target.value })} required />
            <input placeholder="Last Name" value={doctorForm.lastName} onChange={e => setDoctorForm({ ...doctorForm, lastName: e.target.value })} required />
            <input placeholder="Speciality" value={doctorForm.speciality} onChange={e => setDoctorForm({ ...doctorForm, speciality: e.target.value })} required />
            <input placeholder="Phone" value={doctorForm.phone} onChange={e => setDoctorForm({ ...doctorForm, phone: e.target.value })} required />
            <select value={doctorForm.departmentId} onChange={e => setDoctorForm({ ...doctorForm, departmentId: e.target.value })} required>
              <option value="">Select Department</option>
              {departments.map(d => <option key={d.deptId} value={d.deptId}>{d.name}</option>)}
            </select>
            <button type="submit">{doctorForm.doctorId ? "Update Doctor" : "Add Doctor"}</button>
            {doctorForm.doctorId && <button type="button" onClick={resetDoctorForm}>Cancel</button>}
          </form>

          <table>
            <thead><tr><th>ID</th><th>Name</th><th>Speciality</th><th>Phone</th><th>Department</th><th>Actions</th></tr></thead>
            <tbody>
              {doctors.map(doc => (
                <tr key={doc.doctorId}>
                  <td>{doc.doctorId}</td>
                  <td>{doc.firstName} {doc.lastName}</td>
                  <td>{doc.speciality}</td>
                  <td>{doc.phone}</td>
                  <td>{doc.departmentName || "-"}</td>
                  <td>
                    <button onClick={() => editDoctor(doc)}>Edit</button>
                    <button onClick={() => deleteDoctor(doc.doctorId)}>Delete</button>
                    <button onClick={resetDoctorForm}>Add New</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* -------------------- Departments Tab -------------------- */}
      {activeTab === "departments" && (
        <div>
          <form onSubmit={handleDepartmentSubmit}>
            <input placeholder="Name" value={departmentForm.name} onChange={e => setDepartmentForm({ ...departmentForm, name: e.target.value })} required />
            <input placeholder="Location" value={departmentForm.location} onChange={e => setDepartmentForm({ ...departmentForm, location: e.target.value })} />
            <button type="submit">{departmentForm.deptId ? "Update Department" : "Add Department"}</button>
            {departmentForm.deptId && <button type="button" onClick={resetDepartmentForm}>Cancel</button>}
          </form>

          <table>
            <thead><tr><th>ID</th><th>Name</th><th>Location</th><th>Actions</th></tr></thead>
            <tbody>
              {departments.map(dept => (
                <tr key={dept.deptId}>
                  <td>{dept.deptId}</td>
                  <td>{dept.name}</td>
                  <td>{dept.location}</td>
                  <td>
                    <button onClick={() => editDepartment(dept)}>Edit</button>
                    <button onClick={() => deleteDepartment(dept.deptId)}>Delete</button>
                    <button onClick={resetDepartmentForm}>Add New</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* -------------------- Patients Tab -------------------- */}
      {activeTab === "patients" && (
        <div>
          <form onSubmit={handlePatientSubmit}>
            <input placeholder="First Name" value={patientForm.firstName} onChange={e => setPatientForm({ ...patientForm, firstName: e.target.value })} required />
            <input placeholder="Last Name" value={patientForm.lastName} onChange={e => setPatientForm({ ...patientForm, lastName: e.target.value })} required />
            <input placeholder="Gender" value={patientForm.gender} onChange={e => setPatientForm({ ...patientForm, gender: e.target.value })} required />
            <input type="date" placeholder="DOB" value={patientForm.dob} onChange={e => setPatientForm({ ...patientForm, dob: e.target.value })} required />
            <input placeholder="Phone" value={patientForm.phone} onChange={e => setPatientForm({ ...patientForm, phone: e.target.value })} />
            <input placeholder="Address" value={patientForm.address} onChange={e => setPatientForm({ ...patientForm, address: e.target.value })} />
            <button type="submit">{patientForm.patientId ? "Update Patient" : "Add Patient"}</button>
            {patientForm.patientId && <button type="button" onClick={resetPatientForm}>Cancel</button>}
          </form>

          <table>
            <thead><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>DOB</th><th>Phone</th><th>Address</th><th>Actions</th></tr></thead>
            <tbody>
              {patients.map(p => (
                <tr key={p.patientId}>
                  <td>{p.patientId}</td>
                  <td>{p.firstName}</td>
                  <td>{p.lastName}</td>
                  <td>{p.gender}</td>
                  <td>{p.dob}</td>
                  <td>{p.phone}</td>
                  <td>{p.address}</td>
                  <td>
                    <button onClick={() => editPatient(p)}>Edit</button>
                    <button onClick={() => deletePatient(p.patientId)}>Delete</button>
                    <button onClick={resetPatientForm}>Add New</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* -------------------- Appointments Tab -------------------- */}
      {activeTab === "appointments" && (
        <div>
          <form onSubmit={handleAppointmentSubmit}>
            <input type="datetime-local" value={appointmentForm.appointmentTime} onChange={e => setAppointmentForm({ ...appointmentForm, appointmentTime: e.target.value })} required />
            <select value={appointmentForm.doctorId} onChange={e => setAppointmentForm({ ...appointmentForm, doctorId: e.target.value })} required>
              <option value="">Select Doctor</option>
              {doctors.map(d => <option key={d.doctorId} value={d.doctorId}>{d.firstName} {d.lastName}</option>)}
            </select>
            <select value={appointmentForm.patientId} onChange={e => setAppointmentForm({ ...appointmentForm, patientId: e.target.value })} required>
              <option value="">Select Patient</option>
              {patients.map(p => <option key={p.patientId} value={p.patientId}>{p.firstName} {p.lastName}</option>)}
            </select>
            <input placeholder="Reason" value={appointmentForm.reason} onChange={e => setAppointmentForm({ ...appointmentForm, reason: e.target.value })} required />
            <button type="submit">{appointmentForm.apptId ? "Update Appointment" : "Add Appointment"}</button>
            {appointmentForm.apptId && <button type="button" onClick={resetAppointmentForm}>Cancel</button>}
          </form>

          <table>
            <thead><tr><th>ID</th><th>Doctor</th><th>Patient</th><th>Date</th><th>Time</th><th>Reason</th><th>Actions</th></tr></thead>
            <tbody>
              {appointments.map(app => (
                <tr key={app.appt_id}>
                  <td>{app.appt_id}</td>
                  <td>{app.doctor_first} {app.doctor_last}</td>
                  <td>{app.patient_first} {app.patient_last}</td>
                  <td>{app.appt_date}</td>
                  <td>{app.appt_time}</td>
                  <td>{app.reason}</td>
                  <td>
                    <button onClick={() => editAppointment(app)}>Edit</button>
                    <button onClick={() => deleteAppointment(app.appt_id)}>Delete</button>
                    <button onClick={resetAppointmentForm}>Add New</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;
