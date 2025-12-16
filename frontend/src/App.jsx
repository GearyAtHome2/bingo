import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./Login.jsx";
import Bingo from "./Bingo.jsx";

function ProtectedRoute({ children }) {
  const email = localStorage.getItem("email");
  if (!email) {
    // redirect to login if not logged in
    return <Navigate to="/login" replace />;
  }
  return children;
}

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login onSuccess={() => window.location.href = "/bingo"} />} />
      <Route
        path="/bingo"
        element={
          <ProtectedRoute>
            <Bingo />
          </ProtectedRoute>
        }
      />
      <Route path="*" element={<Navigate to="/login" replace />} />
    </Routes>
  );
}
