# GiftCode

A modern GiftCode plugin for Paper servers that allows administrators to create customizable GiftCodes with Vault, Shards, Items, Commands, Permissions, and expiration support.

---

## Features

- Easy-to-use creation GUI
- Custom or Random GiftCodes
- Reward players with:
  - Vault Money
  - Shards
  - Items
  - Console Commands
- Permission-restricted GiftCodes
- Expiration time support
- Usage limits
- Interactive `/gc info`
- Click to copy GiftCode
- PlaceholderAPI support
- Fully configurable messages
- Supports Paper 1.21+

---

## Requirements

| Requirement | Version |
|------------|---------|
| Java | 21+ |
| Minecraft | 1.21+ |
| Server | Paper / Leaf |
| Vault | Optional |
| PlaceholderAPI | Optional |

---

## Installation

1. Download the [latest version](https://github.com/Chanhne-dev/giftcode/releases).

2. Put the plugin into

```text
plugins/
```

3. Install optional dependencies

- Vault
- Shard
- PlaceholderAPI

4. Restart the server.

---

## Features Overview

### Create GiftCode

```
/gc create <code> [uses] [expire]
```

Example

```
/gc create VIP
/gc create VIP 5
/gc create EVENT 1 7d
```

After executing the command, the Create GUI will open.

---

### Create Random GiftCodes

```
/gc create random <amount> [expire]
```

Example

```
/gc create random 100
/gc create random 100 30d
```

The plugin generates random GiftCodes after clicking **Confirm**.

---

### GiftCode Information

```
/gc info <code>
```

Interactive information page.

Features

- Click Code → Copy
- Click Status → Enable / Disable
- Click Items → View rewards
- Click Commands → View commands

---

### Permission Management

Add permission

```
/gc permission <code> add <permission>
```

Remove permission

```
/gc permission <code> delete
```

View permission

```
/gc permission <code> list
```

---

### Other Commands

```
/gc delete <code>

/gc enable <code>

/gc disable <code>

/gc list

/gc reload
```

---

### Redeem GiftCode

```
/code <giftcode>
```

---

## Supported Time Format

```
30s
10m
2h
7d
```

Combination

```
1d 12h
2d 6h 30m
3h 20m 15s
```

Permanent

```
0
never
```

---

## GUI

### Create GiftCode

<img width="344" height="146" alt="Create GUI" src="https://github.com/user-attachments/assets/12fba37b-2081-44a6-8510-4b49d4ae64ff"/>

---

### GiftCode Information

<img width="304" height="202" alt="GiftCode Info" src="https://github.com/user-attachments/assets/73c942d5-c262-48bb-a032-b3f03e18bb25"/>

---

## Permissions

Administrator

```
giftcode.admin
```
---

## Configuration Files

```
plugins/
└── GiftCode/
    ├── Giftcode.yml
    └── messages.yml
```

---

## Contributing

Bug reports and feature requests are welcome.

[GitHub Issues](https://github.com/Chanhne-dev/giftcode/issues)

---

## License

No license has been specified for this project.

---

## Credits

Developer

**Chanhne**

Libraries

- Paper API
- Adventure API
- Vault
- PlaceholderAPI
- SignGUI
- AnvilGUI