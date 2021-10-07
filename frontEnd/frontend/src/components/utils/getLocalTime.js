export default function(nS) {
  return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/, " ");
}