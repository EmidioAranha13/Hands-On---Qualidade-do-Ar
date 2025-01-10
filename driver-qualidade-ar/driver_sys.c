#include <linux/init.h>
#include <linux/module.h>

MODULE_AUTHOR("Equipe Qualidade do Ar <devtitans>");
MODULE_DESCRIPTION("Módulo para o dispositivo de qualidade do ar via USB");
MODULE_LICENSE("GPL");

static ssize_t attr_show(struct kobject *sys_obj, struct kobj_attribute *attr, char *buff);
static ssize_t attr_store(struct kobject *sys_obj, struct kobj_attribute *attr, const char *buff, size_t count);

static struct kobj_attribute  sensor_attribute = __ATTR(sensor, S_IRUGO | S_IWUSR, attr_show, attr_store);
static struct attribute      *attrs[]       = { &sensor_attribute.attr, NULL };
static struct attribute_group attr_group    = { .attrs = attrs };
static struct kobject        *sys_obj;


static int __init module_begin(void) {
    printk(KERN_INFO "Módulo carregado com sucesso!");
    return 0;
}

static void __exit module_end(void) {
    printk(KERN_INFO "Módulo descarregando...");
}

// Executado quando o arquivo /sys/kernel/smartlamp/{sensor} é lido (e.g., cat /sys/kernel/smartlamp/sensor)
static ssize_t attr_show(struct kobject *sys_obj, struct kobj_attribute *attr, char *buff) {
    int value;
    const char *attr_name = attr->attr.name;

    printk(KERN_INFO "SmartLamp: Lendo %s ...\n", attr_name);

    if (!strcmp(attr_name, "sensor"))
        //value = usb_send_cmd("GET_LDR", -1);
        value = 123;

    sprintf(buff, "%d\n", value);
    return strlen(buff);
}

// Executado quando o arquivo /sys/kernel/smartlamp/{sensor} é escrito (e.g., echo "100" | sudo tee -a /sys/kernel/smartlamp/sensor)
static ssize_t attr_store(struct kobject *sys_obj, struct kobj_attribute *attr, const char *buff, size_t count) {
    long ret, value;
    const char *attr_name = attr->attr.name;

    ret = kstrtol(buff, 10, &value);
    if (ret) {
        printk(KERN_ALERT "SmartLamp: valor de %s invalido.\n", attr_name);
        return -EACCES;
    }

    printk(KERN_INFO "SmartLamp: Setando %s para %ld ...\n", attr_name, value);

    if (!strcmp(attr_name, "sensor")){ 
        printk(KERN_ALERT "SmartLamp: o valor do sensor é apenas para leitura.\n");
        return -EACCES;
    }

    if (ret < 0) {
        printk(KERN_ALERT "SmartLamp: erro ao setar o valor do %s.\n", attr_name);
        return -EACCES;
    }

    return strlen(buff);
}


module_init(module_begin);
module_exit(module_end);