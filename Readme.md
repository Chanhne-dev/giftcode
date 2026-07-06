# GiftCode Plugin

Plugin GiftCode cho phép quản trị viên tạo GiftCode để người chơi nhận phần thưởng như:

- 💰 Tiền Vault
- 💎 Shards
- 🎁 Item
- ⚡ Thực thi Commands
- 🔒 Giới hạn bằng Permission
- ⏰ Thời gian hết hạn
- 🔢 Giới hạn số lần sử dụng

---

# Lệnh quản trị

Tất cả các lệnh dưới đây yêu cầu quyền:

```
giftcode.admin
```

---

# Tạo GiftCode

## Tạo GiftCode với tên tùy chỉnh

```
/gc create <code> [uses] [expire]
```

Ví dụ:

```
/gc create VIP
/gc create VIP 5
/gc create VIP 10 7d
/gc create EVENT 1 12h
```

### Tham số

| Tham số | Mô tả |
|---------|------|
| code | Tên GiftCode |
| uses | Số lần sử dụng |
| expire | Thời gian hết hạn |

Nếu không nhập:

- uses = 1
- expire = Không giới hạn

Sau khi nhập lệnh sẽ mở GUI để chỉnh sửa:

- Tiền Vault
- Shards
- Reward Items
- Commands
- Permission

---

# Tạo GiftCode ngẫu nhiên

```
/gc create random <amount> [uses] [expire]
```

Ví dụ:

```
/gc create random 1
/gc create random 10
/gc create random 50 1 7d
```

Ý nghĩa:

```
amount = số GiftCode tạo ra
uses = số lần sử dụng mỗi GiftCode
expire = thời gian hết hạn
```

Ví dụ:

```
/gc create random 100 1 30d
```

sẽ tạo

- 100 GiftCode
- mỗi GiftCode dùng 1 lần
- hết hạn sau 30 ngày

Sau khi nhấn **Confirm**, plugin sẽ liệt kê toàn bộ GiftCode vừa tạo trong chat.

Nhấn vào GiftCode để sao chép.

---

# Xóa GiftCode

```
/gc delete <code>
```

Ví dụ

```
/gc delete VIP
```

---

# Bật GiftCode

```
/gc enable <code>
```

Ví dụ

```
/gc enable VIP
```

---

# Tắt GiftCode

```
/gc disable <code>
```

Ví dụ

```
/gc disable VIP
```

---

# Danh sách GiftCode

```
/gc list
```

Hiển thị toàn bộ GiftCode hiện có.

---

# Reload Plugin

```
/gc reload
```

Reload:

- config.yml
- Giftcode.yml
- messages.yml

---

# Permission GiftCode

Thiết lập quyền cần có để sử dụng GiftCode.

## Thêm Permission

```
/gc permission <code> add <permission>
```

Ví dụ

```
/gc permission VIP add giftcode.vip
```

---

## Xóa Permission

```
/gc permission <code> delete
```

Ví dụ

```
/gc permission VIP delete
```

---

## Xem Permission

```
/gc permission <code> list
```

Ví dụ

```
/gc permission VIP list
```

---

# Người chơi sử dụng GiftCode

```
/code <giftcode>
```

Ví dụ

```
/code VIP2026
```

---

# Định dạng thời gian

Plugin hỗ trợ các định dạng:

```
30s
10m
2h
7d
```

Có thể kết hợp:

```
1d12h
2d6h30m
3h20m15s
```

Không giới hạn thời gian:

```
0
```

hoặc

```
never
```

---

# GUI tạo GiftCode

GUI cho phép chỉnh sửa:

- Tên GiftCode
- Số lần sử dụng
- Thời gian hết hạn
- Tiền Vault
- Shards
- Reward Items
- Commands
- Permission

Nếu tạo bằng:

```
/gc create random
```

thì:

- Không thể chỉnh sửa tên GiftCode
- Tên sẽ được tạo ngẫu nhiên khi nhấn Confirm

---

# Phần thưởng hỗ trợ

Một GiftCode có thể chứa đồng thời:

- Tiền Vault
- Shards
- Reward Items
- Commands

Ví dụ:

```
✔ 100.000$
✔ 50 Shards
✔ Diamond Sword
✔ Key x3
✔ crate give %player_name% vip 1
```

---

# PlaceholderAPI

Plugin hỗ trợ PlaceholderAPI trong:

```
messages.yml
```

Ví dụ:

```
%player_name%
%vault_eco_balance%
```

---

# File dữ liệu

GiftCode được lưu tại:

```
plugins/GiftCode/Giftcode.yml
```

Ngôn ngữ:

```
plugins/GiftCode/messages.yml
```

---

# Quyền

## Quản trị

```
giftcode.admin
```

## Ví dụ Permission GiftCode

```
giftcode.vip
giftcode.mvp
giftcode.legend
giftcode.staff
```

Chỉ người chơi có Permission tương ứng mới có thể sử dụng GiftCode.

---

# Ví dụ tạo GiftCode

## GiftCode cho tất cả người chơi

```
/gc create WELCOME 100 never
```

---

## GiftCode VIP

```
/gc create VIP 20 30d
/gc permission VIP add giftcode.vip
```

---

## Tạo 50 GiftCode ngẫu nhiên

```
/gc create random 50 1 7d
```

Sau khi nhấn Confirm, plugin sẽ hiển thị toàn bộ 50 GiftCode để quản trị viên sao chép và phát cho người chơi.