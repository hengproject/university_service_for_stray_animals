function responseRec(resp) {
  if (resp.code === 200) return true;
  if (resp.code === 403 || resp.code === 401) {
    return false;
  }
  throw resp.msg;
}

export default function (resp) {
  return responseRec(resp);
}
