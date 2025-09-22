#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/fs.h>
#include <linux/miscdevice.h>
#include <linux/device.h>
#include <linux/uaccess.h>
#include <linux/slab.h>
#include <linux/net.h>
#include <linux/socket.h>
#include <linux/skbuff.h>
#include <linux/netdevice.h>
#include <linux/etherdevice.h>
#include <linux/if_tun.h>
#include <linux/if_arp.h>
#include <linux/if_ether.h>
#include <linux/if_packet.h>
#include <linux/if.h>
#include <linux/ip.h>
#include <linux/tcp.h>
#include <linux/udp.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("TryHackMe VPN");
MODULE_DESCRIPTION("TUN device for TryHackMe VPN");
MODULE_VERSION("1.0");

#define TUN_MAJOR 10
#define TUN_MINOR 200

static struct class *tun_class;
static struct device *tun_device;

static int tun_open(struct inode *inode, struct file *file)
{
    printk(KERN_INFO "TUN device opened\n");
    return 0;
}

static int tun_release(struct inode *inode, struct file *file)
{
    printk(KERN_INFO "TUN device closed\n");
    return 0;
}

static ssize_t tun_read(struct file *file, char __user *buf, size_t count, loff_t *ppos)
{
    printk(KERN_INFO "TUN device read\n");
    return 0;
}

static ssize_t tun_write(struct file *file, const char __user *buf, size_t count, loff_t *ppos)
{
    printk(KERN_INFO "TUN device write\n");
    return count;
}

static long tun_ioctl(struct file *file, unsigned int cmd, unsigned long arg)
{
    printk(KERN_INFO "TUN device ioctl\n");
    return 0;
}

static const struct file_operations tun_fops = {
    .owner = THIS_MODULE,
    .open = tun_open,
    .release = tun_release,
    .read = tun_read,
    .write = tun_write,
    .unlocked_ioctl = tun_ioctl,
};

static struct miscdevice tun_misc = {
    .minor = MISC_DYNAMIC_MINOR,
    .name = "tun",
    .fops = &tun_fops,
};

static int __init tun_init(void)
{
    int ret;
    
    printk(KERN_INFO "Loading TUN module for TryHackMe VPN\n");
    
    ret = misc_register(&tun_misc);
    if (ret) {
        printk(KERN_ERR "Failed to register TUN misc device\n");
        return ret;
    }
    
    tun_class = class_create(THIS_MODULE, "tun");
    if (IS_ERR(tun_class)) {
        printk(KERN_ERR "Failed to create TUN class\n");
        misc_deregister(&tun_misc);
        return PTR_ERR(tun_class);
    }
    
    tun_device = device_create(tun_class, NULL, MKDEV(TUN_MAJOR, TUN_MINOR), NULL, "tun");
    if (IS_ERR(tun_device)) {
        printk(KERN_ERR "Failed to create TUN device\n");
        class_destroy(tun_class);
        misc_deregister(&tun_misc);
        return PTR_ERR(tun_device);
    }
    
    printk(KERN_INFO "TUN module loaded successfully\n");
    return 0;
}

static void __exit tun_exit(void)
{
    printk(KERN_INFO "Unloading TUN module\n");
    
    device_destroy(tun_class, MKDEV(TUN_MAJOR, TUN_MINOR));
    class_destroy(tun_class);
    misc_deregister(&tun_misc);
    
    printk(KERN_INFO "TUN module unloaded\n");
}

module_init(tun_init);
module_exit(tun_exit);