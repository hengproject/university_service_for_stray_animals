export default function () {
  window.sessionStorage.clear();
  this.$router.push("/login");
}
